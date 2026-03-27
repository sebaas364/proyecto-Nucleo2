import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router'; 
import { UsuarioService } from '../../services/usuario.service'; 

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule], 
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent {
  usuario = {
    nombre: '',
    email: '',
    password: '',
    rol: 'ESTUDIANTE' 
  };
  
  confirmPassword = '';
  mensajeError = '';

  
  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  // Función para navegar manualmente al login
  irAlLogin() {
    this.router.navigate(['/login']);
  }

  // Proceso de registro hacia Spring Boot
  registrar() {
    this.mensajeError = ''; 

    // Validaciones básicas
    if (!this.usuario.nombre || !this.usuario.email || !this.usuario.password) {
      this.mensajeError = 'Por favor, completa todos los campos.';
      return;
    }
    
    if (this.usuario.password !== this.confirmPassword) {
      this.mensajeError = 'Las contraseñas no coinciden.';
      return;
    }

    // Envío de datos al Backend en Eclipse
    this.usuarioService.registrarUsuario(this.usuario).subscribe({
      next: (respuesta) => {
        alert('¡Cuenta creada con éxito en la Base de Datos de SIPEH!');
        // Redirección automática al Login tras el éxito
        this.irAlLogin();
      },
      error: (err) => {
        console.error('Error en el registro:', err);
        this.mensajeError = 'Hubo un error al registrar. Tal vez el correo ya existe o el servidor está apagado.';
      }
    });
  }
}