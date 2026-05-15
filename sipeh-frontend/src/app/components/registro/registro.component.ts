import { Component, inject } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css',
})
export class RegistroComponent {
  private usuarioService = inject(UsuarioService);
  private router = inject(Router);

  usuario = {
    nombre: '',
    email: '',
    password: '',
    rol: 'ESTUDIANTE', // Valor por defecto
  };

  confirmPassword = '';
  mensajeError = '';
  mensajeExito = '';

  // Función para navegar manualmente al login
  irAlLogin() {
    this.router.navigate(['/login']);
  }

  // Proceso de registro hacia Spring Boot
  // Proceso de registro hacia Spring Boot
  registrar() {
    this.mensajeError = '';
    this.mensajeExito = '';

    // Validaciones básicas
    if (
      !this.usuario.nombre ||
      !this.usuario.email ||
      !this.usuario.password ||
      !this.confirmPassword
    ) {
      this.mensajeError = 'Por favor, completa todos los campos.';
      return;
    }

    if (this.usuario.password !== this.confirmPassword) {
      this.mensajeError = 'Las contraseñas no coinciden.';
      return;
    }

    // Envío de datos al Backend en Eclipse (Corregido a registrarUsuario)
    this.usuarioService.registrarUsuario(this.usuario).subscribe({
      next: () => {
        // Agregado : any
        this.mensajeExito = '¡Cuenta creada con éxito en SIPEH! Redirigiendo...';

        // Redirección automática al Login tras 2 segundos
        setTimeout(() => {
          this.irAlLogin();
        }, 2000);
      },
      error: (err: any) => {
        // Agregado : any
        console.error('Error en el registro:', err);
        this.mensajeError =
          'Hubo un error al registrar. Tal vez el correo ya existe o el servidor Docker está apagado.';
      },
    });
  }
}
