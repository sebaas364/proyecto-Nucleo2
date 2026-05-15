import { Component, inject } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  private usuarioService = inject(UsuarioService);
  private router = inject(Router);

  datosLogin = {
    email: '',
    password: '',
  };

  mensajeError = '';
  mostrarPassword = false;

  togglePassword() {
    this.mostrarPassword = !this.mostrarPassword;
  }

  handleLogin() {
    this.mensajeError = '';

    if (!this.datosLogin.email || !this.datosLogin.password) {
      this.mensajeError = 'Por favor, ingresa tu correo y contraseña.';
      return;
    }

    this.usuarioService.login(this.datosLogin).subscribe({
      next: (usuarioRecibido: any) => {
        alert('¡Inicio de sesión exitoso, Froid!');
        localStorage.setItem('usuarioSIPEH', JSON.stringify(usuarioRecibido));

        // Lógica de enrutamiento basada en el rol
        const rol = usuarioRecibido.rol;

        if (rol === 'ESTUDIANTE') {
          this.router.navigate(['/dashboard-estudiante']);
        } else if (rol === 'DOCENTE') {
          this.router.navigate(['/dashboard-docente']);
        } else if (rol === 'DIRECTOR') {
          this.router.navigate(['/dashboard-director']);
        } else {
          // Salvavidas para la presentación: si falla el rol, te manda al dashboard del decano por defecto
          this.router.navigate(['/dashboard-director']);
        }
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'Correo o contraseña incorrectos.';
      },
    });
  }
}
