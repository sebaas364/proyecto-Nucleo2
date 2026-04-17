import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class DocenteService {
  // Apuntamos a las rutas que acabas de crear en Java
  private apiUrl = 'http://localhost:8080/api/usuarios/docentes';
  private deleteUrl = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) {}

  obtenerDocentes(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
  crearDocente(docente: any): Observable<any> {
    return this.http.post(this.apiUrl, docente);
  }
  eliminarDocente(id: number): Observable<any> {
    return this.http.delete(`${this.deleteUrl}/${id}`);
  }
}
