import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InscripcionService {
  private apiUrl = 'http://localhost:8080/api/inscripciones';

  constructor(private http: HttpClient) { }

  // Buscar materias de un estudiante
  obtenerPorEstudiante(estudianteId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/estudiante/${estudianteId}`);
  }

  // Buscar estudiantes de una materia (Para el Docente luego)
  obtenerPorMateria(materiaId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/materia/${materiaId}`);
  }

  // Inscribir materia
  inscribir(estudianteId: number, materiaId: number): Observable<any> {
    // Mandamos los IDs para que Java construya las relaciones @ManyToOne
    return this.http.post(this.apiUrl, {
      estudiante: { id: estudianteId },
      materia: { id: materiaId }
    });
  }

  // Cancelar inscripción
  cancelarInscripcion(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}