# Comandas My Little Diner

Este proyecto es una aplicación para gestionar comandas en el restaurante "My Little Diner". Incluye funcionalidades para gestionar zonas, mesas, y pedidos de comida y bebida.

## Requisitos de entorno
- **JDK 17** (el plugin de Android requiere al menos Java 17).
- **Android SDK** con la plataforma API 36 y build tools compatibles.
- **Gradle 8.13** (se utiliza el wrapper incluido).
- Opcional: Android Studio (Iguana o superior) para ejecutar y depurar.

## Compilación con Gradle
Desde la raíz del proyecto:

```bash
./gradlew assembleDebug
```

Este comando descarga las dependencias y genera el APK de depuración en `app/build/outputs/apk/debug/`.

Para ejecutar las pruebas unitarias:

```bash
./gradlew test
```

## Ejecución de la aplicación
Con un dispositivo o emulador Android conectado:

```bash
./gradlew installDebug
```

También se puede abrir el proyecto en Android Studio y usar el botón **Run**.

## Capas y dependencias clave
El proyecto está formado por un único módulo `app` con la siguiente organización:

- **Capa de presentación**: `MainActivity` y las pantallas se implementan con Jetpack Compose.
- **Navegación**: `androidx.navigation:navigation-compose`.
- **Ciclo de vida y utilidades**: `androidx.lifecycle:lifecycle-runtime-ktx` y `androidx.core:core-ktx`.
- **Diseño y componentes UI**: `androidx.compose.material3:material3`, `androidx.compose.foundation:foundation` y librerías base de Compose.
- **Animaciones y navegación asistida**: `com.google.accompanist:accompanist-navigation-animation`.

Las versiones de estas dependencias se centralizan en `gradle/libs.versions.toml` y se declaran en `app/build.gradle.kts`.
