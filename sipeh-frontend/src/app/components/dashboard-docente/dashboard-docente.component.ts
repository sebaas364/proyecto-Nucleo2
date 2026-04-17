import { Component, OnInit, ChangeDetectorRef } from '@angular/core'; // <-- 1. Importamos el "Martillo"
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MateriaService } from '../../services/materia.service';
import { InscripcionService } from '../../services/inscripcion.service';

@Component({
  selector: 'app-dashboard-docente',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard-docente.component.html',
  styleUrl: './dashboard-docente.component.css'
})
export class DashboardDocenteComponent implements OnInit {
  usuarioActual: any = null;
  paginaActiva: string = 'dashboard';
  tituloTopbar: string = 'Panel Docente';

  misMaterias: any[] = [];
  dias: string[] = ['LUN', 'MAR', 'MIE', 'JUE', 'VIE', 'SAB'];
  horas: string[] = ['07-09', '09-11', '11-13', '14-16', '16-18', '18-20'];

  materiaSeleccionada: any = null;
  alumnosInscritos: any[] = [];

  constructor(
    private router: Router, 
    private materiaService: MateriaService,
    private inscripcionService: InscripcionService,
    private cdr: ChangeDetectorRef // <-- 2. Lo inyectamos aquí
  ) {}

  ngOnInit(): void {
    const usuarioGuardado = localStorage.getItem('usuarioSIPEH');
    if (usuarioGuardado) {
      this.usuarioActual = JSON.parse(usuarioGuardado);
      this.cargarMisGrupos();
    } else {
      this.router.navigate(['/login']);
    }
  }

  cargarMisGrupos(): void {
    this.materiaService.obtenerMaterias().subscribe(todasLasMaterias => {
      this.misMaterias = todasLasMaterias.filter((m: any) => m.docente && m.docente.id === this.usuarioActual.id);
    });
  }

  verAlumnos(materia: any): void {
    this.materiaSeleccionada = materia;
    this.alumnosInscritos = []; // Limpiamos por seguridad antes de buscar

    this.inscripcionService.obtenerPorMateria(materia.id).subscribe({
      next: (datos) => {
        if (datos && datos.length > 0) {
          // Si Java nos manda datos, los guardamos en la variable
          this.alumnosInscritos = datos.map((ins: any) => ins.estudiante).filter((e: any) => e != null);
        }
        
        // 3. ¡EL MARTILLAZO! 🔨 
        // Obligamos a Angular a revisar las variables y repintar el HTML inmediatamente
        this.cdr.detectChanges();
      },
      error: (err) => console.error("❌ ERROR AL CONECTAR CON JAVA:", err)
    });
  }

  cerrarListaAlumnos(): void {
    this.materiaSeleccionada = null;
    this.alumnosInscritos = [];
  }

  obtenerMateriaEnCelda(dia: string, hora: string): any {
    const prefijoHora = hora.split('-')[0]; 
    return this.misMaterias.find(m => m.horario.includes(dia) && m.horario.includes(prefijoHora));
  }

  obtenerTotalHoras(): number { return this.misMaterias.reduce((total, m) => total + (m.frecuenciaSemanal * 2), 0); }
  obtenerTotalEstudiantes(): number { return this.misMaterias.reduce((total, m) => total + m.cupos, 0); }

  cambiarPagina(pagina: string, titulo: string): void { this.paginaActiva = pagina; this.tituloTopbar = titulo; }
  cerrarSesion(): void { localStorage.removeItem('usuarioSIPEH'); this.router.navigate(['/login']); }
}