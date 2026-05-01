# AiRetailApp 🛍️

AiRetailApp is a modern, high-performance Android application built to showcase a seamless retail experience. It leverages **Jetpack Compose** for a beautiful UI, **Koin** for dependency injection, and **Room** for local persistence, all structured following **Clean Architecture (MVVM)** principles.

## 🚀 Features

- **Product Catalog**: Browse a variety of products in a responsive grid layout.
- **Product Details**: Deep-dive into product information including descriptions and high-quality images.
- **Persistent Favorites**: "Like" your favorite items, with status saved locally in a Room database.
- **AI-Powered Search**: A dedicated bottom-sheet interface for discovering products.
- **Offline First**: Fully functional without an internet connection (cached data and local storage).

## 🛠️ Tech Stack

- **Language**: Kotlin 2.1.0
- **UI**: Jetpack Compose (Material 3)
- **Dependency Injection**: Koin
- **Local Storage**: Room (with KSP)
- **Navigation**: Jetpack Compose Navigation
- **Image Loading**: Coil
- **Concurrency**: Coroutines & Flow

## 🏗️ Architecture

The app is built using **Clean Architecture** to ensure high testability and maintenance:
- **Presentation Layer**: ViewModels and Compose screens.
- **Domain Layer**: Pure Kotlin business logic and models.
- **Data Layer**: Room DB implementation and Repository pattern.

## 📸 Screenshots

*(Add your screenshots here later)*

## 🛠️ Getting Started

### Prerequisites
- Android Studio Ladybug or newer
- JDK 17+
- Android SDK 34+

### Installation
1. Clone the repository.
2. Open the project in Android Studio.
3. Sync Gradle and run the `:app` module.

## 🔮 Roadmap
- [ ] Integration with **Google AI SDK (Gemini)** for semantic product search.
- [ ] AI-driven personalized product recommendations.
- [ ] Cart and Checkout functionality.
- [ ] Unit and UI testing suite.

---
Developed as a showcase of modern Android development best practices.
