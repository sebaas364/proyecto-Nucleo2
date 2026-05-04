import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

// Servicios
import { MateriaService } from '../../services/materia.service';
import { AulaService } from '../../services/aula.service';
import { DocenteService } from '../../services/docente.service';
import { ReportesService } from '../../services/reportes.service';

// Interfaz
import { DecanoDashboardDTO } from './dashboard-director.interface';

@Component({
  selector: 'app-dashboard-director',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard-director.component.html',
  styleUrl: './dashboard-director.component.css'
})
export class DashboardDirectorComponent implements OnInit {
  usuarioActual: any = null;
  paginaActiva: string = 'dashboard';
  tituloTopbar: string = 'Dirección de Programa';

  // Variables para el Sistema de Reportes (Fase 4.2)
  dashboardData: DecanoDashboardDTO | null = null;
  cargandoReporte: boolean = true;
  errorServidor: boolean = false;

  materiasDisponibles: any[] = [];
  aulasDisponibles: any[] = [];
  docentesDisponibles: any[] = [];

  // Objeto para registro de Materias
  nuevaMateria = {
    codigo: '', nombre: '', grupo: '', horario: '', creditos: null, cupos: null,
    frecuenciaSemanal: 1, requisitosAula: '', 
    docente: { id: null }
  };

  // Objeto para registro de Aulas
  nuevaAula = { nombre: '', capacidad: null, caracteristicas: '' };

  // Objeto para registro de Docentes
  nuevoDocente = { 
    nombre: '', email: '', password: '', 
    vinculacion: '', escalafon: '', restriccionesHorario: '' 
  };

  constructor(
    private router: Router, 
    private materiaService: MateriaService, 
    private aulaService: AulaService,
    private docenteService: DocenteService,
    private reportesService: ReportesService
  ) {}

  ngOnInit(): void {
    const usuarioGuardado = localStorage.getItem('usuarioSIPEH');
    if (usuarioGuardado) {
      this.usuarioActual = JSON.parse(usuarioGuardado);
      
      // Carga inicial de datos operativos
      this.cargarMaterias();
      this.cargarAulas();
      this.cargarDocentes();
      
      // Carga de estadísticas para el Dashboard
      this.cargarEstadisticas();
    } else {
      this.router.navigate(['/login']);
    }
  }

  // --- SISTEMA DE REPORTES ---
  cargarEstadisticas(): void {
    this.cargandoReporte = true;
    this.errorServidor = false;

    this.reportesService.getDashboardDecano().subscribe({
      next: (data) => {
        this.dashboardData = data;
        this.cargandoReporte = false; // ¡El relojito se apaga aquí!
      },
      error: (err) => {
        console.error('Error al cargar estadísticas:', err);
        this.errorServidor = true;
        this.cargandoReporte = false;
      }
    });
  }

  // --- CRUD MATERIAS ---
  cargarMaterias(): void {
    this.materiaService.obtenerMaterias().subscribe(datos => this.materiasDisponibles = datos);
  }

  registrarMateria(): void {
    if (!this.nuevaMateria.codigo || !this.nuevaMateria.nombre) {
      alert('⚠️ Código y Nombre son obligatorios.');
      return;
    }

    const materiaAGuardar = { ...this.nuevaMateria };
    if (!materiaAGuardar.docente.id) {
      materiaAGuardar.docente = null as any; 
    }

    this.materiaService.crearMateria(materiaAGuardar).subscribe({
      next: (materiaGuardada) => {
        alert('✅ ¡Materia creada y asociada exitosamente!');
        this.materiasDisponibles.push(materiaGuardada);
        this.cargarEstadisticas(); // Recargar reporte al haber cambios
        this.nuevaMateria = { codigo: '', nombre: '', grupo: '', horario: '', creditos: null, cupos: null, frecuenciaSemanal: 1, requisitosAula: '', docente: { id: null } };
      },
      error: (err) => alert('❌ Error al guardar la materia.')
    });
  }

  borrarMateria(id: number): void {
    if(confirm('¿Estás seguro de que deseas eliminar esta materia?')) {
      this.materiaService.eliminarMateria(id).subscribe(() => {
        this.materiasDisponibles = this.materiasDisponibles.filter(m => m.id !== id);
        this.cargarEstadisticas(); // Recargar reporte
        alert('🗑️ Materia eliminada.');
      });
    }
  }

  // --- CRUD AULAS ---
  cargarAulas(): void {
    this.aulaService.obtenerAulas().subscribe(datos => this.aulasDisponibles = datos);
  }

  registrarAula(): void {
    if (!this.nuevaAula.nombre || !this.nuevaAula.capacidad) {
      alert('⚠️ Nombre y capacidad son obligatorios.');
      return;
    }
    this.aulaService.crearAula(this.nuevaAula).subscribe({
      next: (aulaGuardada) => {
        alert('✅ ¡Aula registrada exitosamente!');
        this.aulasDisponibles.push(aulaGuardada);
        this.nuevaAula = { nombre: '', capacidad: null, caracteristicas: '' };
      },
      error: (err) => alert('❌ Error al guardar el aula.')
    });
  }

  borrarAula(id: number): void {
    if(confirm('¿Seguro que deseas eliminar esta aula?')) {
      this.aulaService.eliminarAula(id).subscribe(() => {
        this.aulasDisponibles = this.aulasDisponibles.filter(a => a.id !== id);
        alert('🗑️ Aula eliminada.');
      });
    }
  }

  // --- CRUD DOCENTES ---
  cargarDocentes(): void {
    this.docenteService.obtenerDocentes().subscribe(datos => this.docentesDisponibles = datos);
  }

  registrarDocente(): void {
    if (!this.nuevoDocente.nombre || !this.nuevoDocente.email || !this.nuevoDocente.password) {
      alert('⚠️ Nombre, Email y Contraseña son obligatorios.');
      return;
    }
    this.docenteService.crearDocente(this.nuevoDocente).subscribe({
      next: (docenteGuardado) => {
        alert('✅ ¡Docente registrado!');
        this.docentesDisponibles.push(docenteGuardado);
        this.nuevoDocente = { nombre: '', email: '', password: '', vinculacion: '', escalafon: '', restriccionesHorario: '' };
      },
      error: (err) => alert('❌ Error al registrar al docente.')
    });
  }

  borrarDocente(id: number): void {
    if(confirm('¿Seguro que deseas eliminar a este docente?')) {
      this.docenteService.eliminarDocente(id).subscribe(() => {
        this.docentesDisponibles = this.docentesDisponibles.filter(d => d.id !== id);
        alert('🗑️ Docente eliminado.');
      });
    }
  }

  cambiarPagina(pagina: string, titulo: string): void { 
    this.paginaActiva = pagina; 
    this.tituloTopbar = titulo; 
    if (pagina === 'dashboard') {
      this.cargarEstadisticas(); // Refrescar datos al volver al dashboard
    }
  }
  
  cerrarSesion(): void { 
    localStorage.removeItem('usuarioSIPEH'); 
    this.router.navigate(['/login']); 
  }
}