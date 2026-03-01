# Lightning App :rocket:  

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple.svg)](https://kotlinlang.org/)  
[![Compose](https://img.shields.io/badge/Jetpack_Compose-1.5.0-green.svg)](https://developer.android.com/jetpack/compose)  
[![Firebase](https://img.shields.io/badge/Firebase-Auth_&_Firestore-orange.svg)](https://firebase.google.com/)  

**Lightning** es una aplicación de Android moderna diseñada para la gestión de actividades y sincronización en tiempo real.  
Construida bajo los principios de **Clean Architecture** y las últimas tendencias en desarrollo nativo.

---

## :mobile_phone: Capturas de Pantalla

| Login | Registro |
|-------|----------|
| ![Login](screenshots/login) | ![Register](screenshots/register) |
---

## :sparkles: Características

- **Autenticación:**  
  Sistema de Login y Registro seguro mediante Firebase Auth.

- **Base de Datos en Tiempo Real:**  
  Gestión de actividades (CRUD) sincronizada con Cloud Firestore.

- **Arquitectura Limpia:**  
  Separación estricta de responsabilidades (Data, Domain, Presentation).

- **Inyección de Dependencias:**  
  Gestión eficiente de componentes con Koin.

- **UI Reactiva:**  
  Interfaz construida íntegramente con Jetpack Compose y Material3.

---

## :tools: Stack Tecnológico

- **Lenguaje:** Kotlin + Corrutinas & Flow.  
- **UI:** Jetpack Compose (Material 3).  
- **Backend:** Firebase Authentication & Cloud Firestore.  
- **DI:** Koin (Dependency Injection).  
- **Navegación:** Jetpack Navigation Compose.  
- **Arquitectura:** MVVM (Model-View-ViewModel) + Use Cases.

---

## :construction_site: Estructura del Proyecto

```text
app/
├── data/          # Implementación de repositorios y fuentes de datos (Firebase)
├── domain/        # Modelos de negocio y Use Cases (Lógica pura)
├── presentation/  # UI (Screens, ViewModels, Theme)
└── di/            # Módulos de Koin
```

---

## 🚀 Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/tu-usuario/wargame-app.git
```

2. Añade tu archivo `google-services.json` en la carpeta `app/`.

3. Compila y ejecuta en Android Studio.

---

Creado por **Alejandro Zagastizabal**  
Estudiante de Informática.

---

### 💡 Consejos extra para tu GitHub

1. **Sustituye las capturas:**  
   Cambia los links de `placeholder.com` por capturas reales de tu app. Puedes subirlas a una carpeta llamada `screenshots` en el mismo repo.

2. **Añade una Licencia:**  
   En GitHub, dale a *"Add file"* → *"Create new file"* y escribe `LICENSE`.  
   Elige la **MIT License**, es la estándar para proyectos de estudiantes.
