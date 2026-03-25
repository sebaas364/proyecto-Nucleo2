import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  // Esta es la ruta exacta de tu Spring Boot
  private apiUrl = 'http://localhost:8080/api/usuarios'; 

  constructor(private http: HttpClient) { }

  // Método para guardar un usuario nuevo
  registrarUsuario(usuario: any): Observable<any> {
    return this.http.post(this.apiUrl, usuario);
  }
  login(datos: any): Observable<any> {
  return this.http.post(`${this.apiUrl}/login`, datos);
}
}