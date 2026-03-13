function handleRegister() {
  const nombre = document.getElementById('reg-nombre').value;
  const email = document.getElementById('reg-email').value;
  const password = document.getElementById('reg-password').value;
  const confirmPassword = document.getElementById('reg-confirm-password').value;
  const errorMessage = document.getElementById('reg-error');

  if (nombre === '' || email === '' || password === '') {
    errorMessage.textContent = 'Por favor, completa todos los campos.';
    errorMessage.style.display = 'block';
    return;
  }

  if (password !== confirmPassword) {
    errorMessage.textContent = 'Las contraseñas no coinciden.';
    errorMessage.style.display = 'block';
    return;
  }

  const nuevoUsuario = {
    nombre: nombre,
    email: email,
    password: password
  };

  localStorage.setItem('usuarioSIPEH', JSON.stringify(nuevoUsuario));

  errorMessage.style.display = 'none';
  alert('¡Cuenta creada con éxito! Ahora puedes iniciar sesión.');
  window.location.href = 'sipeh_estudiante.html';
}