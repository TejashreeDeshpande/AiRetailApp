# System Design Document: AiRetailApp

## 1. Overview
AiRetailApp is a modern Android application designed for a retail environment. It features a persistent product catalog, item interactions (liking), detailed product views, and an AI-driven search interface. The app is built with Kotlin, Jetpack Compose, and follows Clean Architecture principles.

## 2. High-Level Architecture
The application follows the **MVVM (Model-View-ViewModel)** pattern combined with **Clean Architecture** to ensure separation of concerns, testability, and maintainability.

### Layers:
- **UI Layer (Presentation)**: Jetpack Compose for declarative UI, ViewModels to manage UI state using `StateFlow`.
- **Domain Layer**: Contains business logic, including data models (`Product`) and repository interfaces. This layer is independent of any platform-specific frameworks.
- **Data Layer**: Responsible for data persistence and retrieval. It implements repository interfaces and interacts with the Room database and potentially external APIs.

## 3. Technology Stack
- **Language**: Kotlin 2.1.0
- **UI Framework**: Jetpack Compose
- **Dependency Injection**: Koin 4.0.0
- **Local Database**: Room 2.7.0-alpha12 (with KSP support)
- **Navigation**: Jetpack Compose Navigation
- **Image Loading**: Coil
- **Concurrency**: Kotlin Coroutines & Flow

## 4. Data Layer & Persistence
The app uses **Room** for local storage, ensuring offline capability and persistence of user interactions (like/unlike).

### Database Schema (`AppDatabase`):
- **ProductEntity**:
    - `id` (Primary Key)
    - `name`, `description`, `price`, `imageUrl`, `category`
    - `isLiked` (Boolean) - Persists the favorite status.

### Repository Pattern:
- `ProductRepository`: Interface defining data operations.
- `ProductRepositoryImpl`: Concrete implementation using `ProductDao` to fetch and update product data.
- Initial data is seeded into the database on the first app launch via `AiRetailApplication`.

## 5. UI Layer Implementation
The UI is built entirely with **Jetpack Compose**, organized into screens and reusable components.

### Screens:
- **HomeScreen**: Displays a grid of products. Includes a Floating Action Button (FAB) for search.
- **ProductDetailScreen**: Detailed view of a selected product with a dedicated "Like" action in the TopAppBar.
- **AiSearchBottomSheet**: A modal interface for natural language product discovery (currently mapped to local SQL search).

### State Management:
- **ProductViewModel**: Exposes `products` and `searchResult` as `StateFlow`. Uses `viewModelScope` for database operations.

## 6. Dependency Injection (Koin)
Koin is used for managing dependencies across the app.
- **appModule**: Defines singletons for the Room Database, DAO, and Repository, and provides `viewModel` definitions.

## 7. Navigation
Uses the `navigation-compose` library.
- **AppNavGraph**: Defines routes (`home`, `product_detail/{productId}`) and handles transitions between screens, passing arguments like `productId` where necessary.

## 8. AI Capabilities (Current & Future)
- **Current**: Search is implemented using SQL `LIKE` queries in Room for names and descriptions.
- **Future Integration**: 
    - Integration of **Google AI SDK** (Gemini) for advanced semantic search and personalized recommendations.
    - AI-generated product descriptions or summaries.
    - Image-based search capabilities.

## 9. Security & Permissions
- **INTERNET**: Required for loading product images via Coil.
- **Database Safety**: Uses versioning and `fallbackToDestructiveMigration` during current development phases to handle schema updates.
