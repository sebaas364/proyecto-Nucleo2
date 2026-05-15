import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DecanoDashboardDTO } from '../components/dashboard-director/dashboard-director.interface'; // Ajusta la ruta

@Injectable({
  providedIn: 'root',
})
export class ReportesService {
  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/api/v1/reportes';

  getDashboardDecano(): Observable<DecanoDashboardDTO> {
    return this.http.get<DecanoDashboardDTO>(`${this.apiUrl}/decano/dashboard`);
  }
}
