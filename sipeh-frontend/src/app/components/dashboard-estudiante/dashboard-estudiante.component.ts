import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MateriaService } from '../../services/materia.service';
import { InscripcionService } from '../../services/inscripcion.service';

@Component({
  selector: 'app-dashboard-estudiante',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard-estudiante.component.html',
  styleUrl: './dashboard-estudiante.component.css'
})
export class DashboardEstudianteComponent implements OnInit {
  usuarioActual: any = null;
  paginaActiva: string = 'dashboard';
  tituloTopbar: string = 'Dashboard';

  materiasDisponibles: any[] = [];
  inscripciones: any[] = []; // Guarda la conexión real con la base de datos (IDs de inscripción)
  materiasInscritas: any[] = []; // Extrae solo la materia para dibujar la grilla visual

  dias: string[] = ['LUN', 'MAR', 'MIE', 'JUE', 'VIE', 'SAB'];
  horas: string[] = ['07-09', '09-11', '11-13', '14-16', '16-18', '18-20'];

  constructor(
    private router: Router,
    private materiaService: MateriaService,
    private inscripcionService: InscripcionService
  ) {}

  ngOnInit(): void {
    const usuarioGuardado = localStorage.getItem('usuarioSIPEH');
    if (usuarioGuardado) {
      this.usuarioActual = JSON.parse(usuarioGuardado);
      this.cargarMaterias();
      this.cargarInscripciones();
    } else {
      this.router.navigate(['/login']);
    }
  }

  cargarMaterias(): void {
    this.materiaService.obtenerMaterias().subscribe(datos => this.materiasDisponibles = datos);
  }

  // Consulta en Java las materias reales del estudiante
  cargarInscripciones(): void {
    this.inscripcionService.obtenerPorEstudiante(this.usuarioActual.id).subscribe(datos => {
      this.inscripciones = datos;
      this.materiasInscritas = datos.map((ins: any) => ins.materia);
    });
  }

  obtenerCreditosTotales(): number {
    return this.materiasInscritas.reduce((total, mat) => total + mat.creditos, 0);
  }

  inscribirMateria(materia: any): void {
    // 1. Validación de seguridad: Si la materia no tiene horario, bloqueamos la inscripción
    if (!materia.horario) {
      alert('❌ Esta materia no tiene un horario asignado por la dirección. No se puede inscribir.');
      return;
    }

    // 2. Validación de Créditos
    if (this.obtenerCreditosTotales() + materia.creditos > 20) {
      alert('❌ LÍMITE DE CRÉDITOS: No puedes exceder los 20 créditos por semestre.');
      return;
    }

    // 3. Validación de Materia Repetida
    if (this.materiasInscritas.some(m => m.id === materia.id)) {
      alert('⚠️ Ya tienes inscrita esta materia.');
      return;
    }

    // 4. Validación de Cruce de Horario (Blindada contra nulls)
    const horarioMateria = materia.horario || "";
    const partes = horarioMateria.split(' ');
    const dia = partes[0];
    const horaInicio = partes[1] ? partes[1].split('-')[0] : "";

    const cruce = this.materiasInscritas.find(m => 
      m.horario && m.horario.includes(dia) && m.horario.includes(horaInicio)
    );

    if (cruce) {
      alert(`❌ CRUCE DE HORARIO: No puedes inscribir "${materia.nombre}" porque choca con "${cruce.nombre}".`);
      return;
    }

    // 5. Si superó todas las pruebas, INSCRIBIMOS EN LA BASE DE DATOS
    this.inscripcionService.inscribir(this.usuarioActual.id, materia.id).subscribe({
      next: (nuevaInscripcion) => {
        alert('✅ ¡Materia inscrita exitosamente!');
        this.inscripciones.push(nuevaInscripcion);
        this.materiasInscritas.push(nuevaInscripcion.materia);
      },
      error: (err) => {
        console.error(err);
        alert('❌ Hubo un error al guardar tu inscripción en el sistema.');
      }
    });
  }

  cancelarInscripcion(materia: any): void {
    if(confirm(`¿Estás seguro de cancelar tu inscripción a ${materia.nombre}?`)) {
      // Encontramos el ID real de la inscripción en la base de datos
      const inscripcionAEliminar = this.inscripciones.find(ins => ins.materia.id === materia.id);
      
      if(inscripcionAEliminar) {
        this.inscripcionService.cancelarInscripcion(inscripcionAEliminar.id).subscribe(() => {
          // Actualizamos la vista instantáneamente
          this.inscripciones = this.inscripciones.filter(ins => ins.id !== inscripcionAEliminar.id);
          this.materiasInscritas = this.materiasInscritas.filter(m => m.id !== materia.id);
          alert('🗑️ Inscripción cancelada.');
        });
      }
    }
  }

  // Lógica de la grilla (Blindada contra nulls)
  obtenerMateriaEnCelda(dia: string, hora: string): any {
    const prefijoHora = hora.split('-')[0];
    return this.materiasInscritas.find(m => 
      m.horario && m.horario.includes(dia) && m.horario.includes(prefijoHora)
    );
  }

  cambiarPagina(pagina: string, titulo: string): void {
    this.paginaActiva = pagina;
    if(titulo) this.tituloTopbar = titulo;
  }

  cerrarSesion(): void {
    localStorage.removeItem('usuarioSIPEH');
    this.router.navigate(['/login']);
  }
}