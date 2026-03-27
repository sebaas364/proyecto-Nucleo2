import { Routes } from '@angular/router';
import { RegistroComponent } from './components/registro/registro.component';
import { LoginComponent } from './components/login/login.component'

export const routes: Routes = [
  { path: '', component: RegistroComponent }, // Pantalla inicial
  { path: 'login', component: LoginComponent } // Nueva ruta: localhost:4200/login
];