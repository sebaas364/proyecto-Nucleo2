import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AulaService {
  private apiUrl = 'http://localhost:8080/api/aulas';
  constructor(private http: HttpClient) {}

  obtenerAulas(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
  crearAula(aula: any): Observable<any> {
    return this.http.post(this.apiUrl, aula);
  }
  eliminarAula(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
