let currentClassSelection = {};
let totalCredits = 11;
let totalCourses = 3;

document.addEventListener("DOMContentLoaded", () => {
  const usuarioGuardado = JSON.parse(localStorage.getItem("usuarioSIPEH"));
  if (usuarioGuardado && usuarioGuardado.nombre) {
    const titleEl = document.querySelector(".page-title");
    if (titleEl)
      titleEl.innerHTML = `¡Bienvenido, ${usuarioGuardado.nombre}! 👋`;

    const unameEl = document.querySelector(".sb-uname");
    if (unameEl) unameEl.textContent = usuarioGuardado.nombre;

    const avatarEl = document.querySelector(".sb-avatar");
    if (avatarEl) {
      const initials = usuarioGuardado.nombre
        .split(" ")
        .map((n) => n[0])
        .join("")
        .substring(0, 2)
        .toUpperCase();
      avatarEl.textContent = initials;
    }
  }
});

function navigatePage(pageId, navElement) {
  document.querySelectorAll(".page").forEach((page) => {
    page.classList.remove("active");
  });

  document.querySelectorAll(".sb-item").forEach((item) => {
    item.classList.remove("active");
  });

  document.getElementById(`page-${pageId}`).classList.add("active");

  if (navElement) {
    navElement.classList.add("active");
  }

  const sectionTitles = {
    dashboard: "Dashboard",
    oferta: "Oferta Académica",
    horario: "Mi Horario",
  };
  document.getElementById("topbar-title").textContent =
    sectionTitles[pageId] || pageId;
}

function handleLogout() {
  window.location.href = "sipeh_estudiante.html";
}

function openEnrollmentModal(
  courseCode,
  courseName,
  professor,
  schedule,
  classroom,
  credits,
) {
  currentClassSelection = {
    code: courseCode,
    name: courseName,
    credits: parseInt(credits, 10),
  };

  const modalInfoContainer = document.getElementById("modal-info");
  modalInfoContainer.innerHTML = `
    <p>📌 <strong>Curso:</strong> ${courseCode} — ${courseName}</p>
    <p>👨‍🏫 <strong>Docente:</strong> ${professor}</p>
    <p>🕐 <strong>Horario:</strong> ${schedule}</p>
    <p>🏫 <strong>Aula:</strong> ${classroom}</p>
    <p>🏅 <strong>Créditos:</strong> ${credits}</p>
  `;
  document.getElementById("modal").classList.add("open");
}

function closeModal() {
  document.getElementById("modal").classList.remove("open");
}

function confirmEnrollment() {
  closeModal();
  totalCredits += currentClassSelection.credits;
  totalCourses += 1;
  updateDashboardCounters();
  displayToastNotification(
    `✓ Inscripción en ${currentClassSelection.code} confirmada`,
    "success",
  );
}

function cancelClassEnrollment(buttonElement, courseCode, courseCredits) {
  const isConfirmed = confirm(
    `¿Estás seguro de que deseas cancelar la inscripción de ${courseCode}?`,
  );
  if (!isConfirmed) return;

  const codeElements = document.querySelectorAll(".cb-code");
  codeElements.forEach((element) => {
    if (element.textContent === courseCode) {
      element.closest(".grilla-cell").innerHTML = "";
    }
  });

  totalCredits -= courseCredits;
  totalCourses -= 1;
  updateDashboardCounters();
  displayToastNotification(`Inscripción de ${courseCode} cancelada`, "error");
}

function updateDashboardCounters() {
  document.getElementById("dash-creditos").textContent = totalCredits;
  document.getElementById("dash-materias").textContent = totalCourses;
  document.getElementById("cr-display").textContent = totalCredits;
  const progressPercentage = (totalCredits / 20) * 100;
  document.getElementById("cr-bar-fill").style.width = `${progressPercentage}%`;
}

function displayToastNotification(message, type = "") {
  const toastElement = document.getElementById("toast");
  toastElement.textContent = message;
  toastElement.className = `toast ${type} show`;
  setTimeout(() => {
    toastElement.classList.remove("show");
  }, 3000);
}

document.getElementById("modal").addEventListener("click", function (event) {
  if (event.target === this) closeModal();
});
