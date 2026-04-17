import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; // Para navegar al dashboard tras el éxito
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  // Datos que el usuario escribe
  datosLogin = {
    email: '',
    password: ''
  };

  mensajeError = '';
  mostrarPassword = false;

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  togglePassword() {
    this.mostrarPassword = !this.mostrarPassword;
  }

handleLogin() {
    this.mensajeError = '';

    if (!this.datosLogin.email || !this.datosLogin.password) {
      this.mensajeError = 'Por favor, ingresa tu correo y contraseña.';
      return;
    }

    // Llamamos al servicio de SIPEH
    this.usuarioService.login(this.datosLogin).subscribe({
      next: (usuarioRecibido: any) => {
        
        // Guardamos la sesión
        localStorage.setItem('usuarioSIPEH', JSON.stringify(usuarioRecibido));

        // 🚀 Lógica de enrutamiento basada en el rol
        const rol = usuarioRecibido.rol;

        if (rol === 'ESTUDIANTE') {
          this.router.navigate(['/dashboard-estudiante']);
        } else if (rol === 'DOCENTE') {
          this.router.navigate(['/dashboard-docente']);
        } else if (rol === 'DIRECTOR') {
          this.router.navigate(['/dashboard-director']);
        } else {
          this.mensajeError = 'Tu cuenta no tiene un rol válido asignado.';
        }
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'Correo o contraseña incorrectos.';
      }
    });
  

    // Llamamos al servicio (necesitaremos agregar este método en el servicio)
    this.usuarioService.login(this.datosLogin).subscribe({
      next: (usuarioRecibido) => {
        console.log('Bienvenido:', usuarioRecibido);
        alert('¡Inicio de sesión exitoso, Froid!');
        // Aquí podrías guardar el usuario en localStorage si quieres
        localStorage.setItem('usuarioSIPEH', JSON.stringify(usuarioRecibido));
        // this.router.navigate(['/dashboard']); // Cuando tengas la otra pantalla
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'Correo o contraseña incorrectos.';
      }
    });
  }
}