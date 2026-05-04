import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DecanoDashboardDTO } from '../components/dashboard-director/dashboard-director.interface'; // Ajusta la ruta

@Injectable({
  providedIn: 'root'
})
export class ReportesService {

 private apiUrl = 'http://localhost:8080/api/v1/reportes';

  constructor(private http: HttpClient) { }

  getDashboardDecano(): Observable<DecanoDashboardDTO> {
    return this.http.get<DecanoDashboardDTO>(`${this.apiUrl}/decano/dashboard`);
  }
}