import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MateriaService } from '../../services/materia.service';
import { AulaService } from '../../services/aula.service';
import { DocenteService } from '../../services/docente.service';

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

  materiasDisponibles: any[] = [];
  aulasDisponibles: any[] = [];
  docentesDisponibles: any[] = [];

  // Objeto para US-09, US-10 y US-11 (Materias) + US-06 (Docente asignado)
  nuevaMateria = {
    codigo: '', nombre: '', grupo: '', horario: '', creditos: null, cupos: null,
    frecuenciaSemanal: 1, requisitosAula: '', 
    docente: { id: null } // El ID del profesor seleccionado
  };

  // Objeto para US-12 (Aulas)
  nuevaAula = { nombre: '', capacidad: null, caracteristicas: '' };

  // Objeto para US-05 y US-07 (Docentes)
  nuevoDocente = { 
    nombre: '', email: '', password: '', 
    vinculacion: '', escalafon: '', restriccionesHorario: '' 
  };

  constructor(
    private router: Router, 
    private materiaService: MateriaService, 
    private aulaService: AulaService,
    private docenteService: DocenteService
  ) {}

  ngOnInit(): void {
    const usuarioGuardado = localStorage.getItem('usuarioSIPEH');
    if (usuarioGuardado) {
      this.usuarioActual = JSON.parse(usuarioGuardado);
      this.cargarMaterias();
      this.cargarAulas();
      this.cargarDocentes();
    } else {
      this.router.navigate(['/login']);
    }
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

    // Prevenir enviar un objeto vacío si no se selecciona docente
    const materiaAGuardar = { ...this.nuevaMateria };
    if (!materiaAGuardar.docente.id) {
      materiaAGuardar.docente = null as any; 
    }

    this.materiaService.crearMateria(materiaAGuardar).subscribe({
      next: (materiaGuardada) => {
        alert('✅ ¡Materia creada y asociada exitosamente!');
        this.materiasDisponibles.push(materiaGuardada);
        // Limpiar el formulario
        this.nuevaMateria = { codigo: '', nombre: '', grupo: '', horario: '', creditos: null, cupos: null, frecuenciaSemanal: 1, requisitosAula: '', docente: { id: null } };
      },
      error: (err) => alert('❌ Error al guardar la materia. Revisa la consola.')
    });
  }

  borrarMateria(id: number): void {
    if(confirm('¿Estás seguro de que deseas eliminar esta materia del sistema?')) {
      this.materiaService.eliminarMateria(id).subscribe(() => {
        this.materiasDisponibles = this.materiasDisponibles.filter(m => m.id !== id);
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
    if(confirm('¿Seguro que deseas dar de baja este espacio físico?')) {
      this.aulaService.eliminarAula(id).subscribe(() => {
        this.aulasDisponibles = this.aulasDisponibles.filter(a => a.id !== id);
        alert('🗑️ Aula eliminada de la infraestructura.');
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
        alert('✅ ¡Docente registrado en la plantilla!');
        this.docentesDisponibles.push(docenteGuardado);
        this.nuevoDocente = { nombre: '', email: '', password: '', vinculacion: '', escalafon: '', restriccionesHorario: '' };
      },
      error: (err) => alert('❌ Error al registrar al docente.')
    });
  }

  borrarDocente(id: number): void {
    if(confirm('¿Seguro que deseas desvincular a este docente del sistema?')) {
      this.docenteService.eliminarDocente(id).subscribe(() => {
        this.docentesDisponibles = this.docentesDisponibles.filter(d => d.id !== id);
        alert('🗑️ Docente eliminado.');
      });
    }
  }

  cambiarPagina(pagina: string, titulo: string): void { 
    this.paginaActiva = pagina; 
    this.tituloTopbar = titulo; 
  }
  
  cerrarSesion(): void { 
    localStorage.removeItem('usuarioSIPEH'); 
    this.router.navigate(['/login']); 
  }
}