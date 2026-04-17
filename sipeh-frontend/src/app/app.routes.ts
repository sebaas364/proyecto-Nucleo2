import { Routes } from '@angular/router';
import { RegistroComponent } from './components/registro/registro.component';
import { LoginComponent } from './components/login/login.component';

// Importamos los nuevos componentes de los dashboards
import { DashboardEstudianteComponent } from './components/dashboard-estudiante/dashboard-estudiante.component';
import { DashboardDocenteComponent } from './components/dashboard-docente/dashboard-docente.component';
import { DashboardDirectorComponent } from './components/dashboard-director/dashboard-director.component';

export const routes: Routes = [
  { path: '', component: RegistroComponent }, // Pantalla inicial
  { path: 'login', component: LoginComponent },
  
  
  // Nuevas rutas protegidas por rol
  { path: 'dashboard-estudiante', component: DashboardEstudianteComponent },
  { path: 'dashboard-docente', component: DashboardDocenteComponent },
  { path: 'dashboard-director', component: DashboardDirectorComponent },
  
  // Opcional: Si escriben una ruta que no existe, los mandamos al login
  { path: '**', redirectTo: 'login', pathMatch: 'full' }
];