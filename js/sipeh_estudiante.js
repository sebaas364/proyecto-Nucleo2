function togglePasswordVisibility() {
  const passwordInput = document.getElementById("login-password");
  if (passwordInput.type === "password") {
    passwordInput.type = "text";
  } else {
    passwordInput.type = "password";
  }
}

function handleLogin() {
  const emailInput = document.getElementById("login-email").value;
  const passwordInput = document.getElementById("login-password").value;
  const errorMessage = document.getElementById("login-error");

  const usuarioGuardado = JSON.parse(localStorage.getItem("usuarioSIPEH"));

  if (emailInput === "" || passwordInput === "") {
    errorMessage.textContent = "Por favor, ingresa tu correo y contraseña.";
    errorMessage.style.display = "block";
    return;
  }

  if (
    usuarioGuardado &&
    emailInput === usuarioGuardado.email &&
    passwordInput === usuarioGuardado.password
  ) {
    errorMessage.style.display = "none";
    window.location.href = "dashboard.html";
  } else {
    errorMessage.textContent =
      "Correo o contraseña incorrectos, o usuario no registrado.";
    errorMessage.style.display = "block";
  }
}
