import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class MateriaService {
  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/api/materias';

  obtenerMaterias(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
  crearMateria(materia: any): Observable<any> {
    return this.http.post(this.apiUrl, materia);
  }
  eliminarMateria(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
