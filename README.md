# Portfolio

A modern, cross-platform portfolio application built with Kotlin Multiplatform and Compose Multiplatform, showcasing professional work and projects across Android, iOS, Web, and Desktop platforms.

## Description

This portfolio application demonstrates the power of Kotlin Multiplatform by delivering a consistent user experience across multiple platforms with a single shared codebase. The app features a clean, responsive design that adapts seamlessly to different screen sizes and platform conventions.

## Features

- **Cross-Platform Support**: Runs natively on Android, iOS, Web (Wasm/JS), and Desktop (JVM)
- **Responsive Design**: Adaptive UI that works beautifully on mobile, tablet, and desktop screens
- **Project Showcase**: Display portfolio projects with descriptions, images, and links
- **About Section**: Professional profile and biography
- **Skills & Technologies**: Highlight technical expertise and competencies
- **Contact Information**: Easy ways to connect and get in touch
- **Smooth Navigation**: Intuitive user interface with seamless transitions
- **Dark/Light Theme Support**: Customizable appearance preferences

## Technologies Used

### Core
- **Kotlin Multiplatform**: Share business logic across all platforms
- **Compose Multiplatform**: Unified UI framework for declarative interfaces
- **Kotlin/Wasm**: High-performance web applications
- **Kotlin/JS**: Web compatibility for broader browser support

### Platform-Specific
- **Android**: Native Android app with Jetpack Compose
- **iOS**: Native iOS app with Compose for iOS
- **Desktop (JVM)**: Cross-platform desktop application
- **Web**: Progressive web application with Wasm/JS support

### Build & Tools
- **Gradle**: Build automation and dependency management
- **Kotlin 2.x**: Modern programming language features

## Project Structure

* [/composeApp](./composeApp/src) contains the shared code for all platforms
  - [commonMain](./composeApp/src/commonMain/kotlin) - Shared code for all targets
  - Platform-specific folders for Android, iOS, Desktop (JVM), and Web implementations

* [/iosApp](./iosApp/iosApp) contains the iOS application entry point

## Contact Information

- **Email**: manon.ronjiediafante@gmail.com
- **LinkedIn**: [Your LinkedIn Profile](https://www.linkedin.com/in/ronjiemanon)
- **GitHub**: [Your GitHub Profile](https://github.com/itsmeronjie)
- **Portfolio Website**: [yourwebsite.com](https://https://portfolio-rdmanon.vercel.app/)


---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…
