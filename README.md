# ChessK

## 1. Description
**ChessK** is a full-stack, real-time multiplayer chess platform engineered using Kotlin Multiplatform (KMP) and Compose Multiplatform for client applications, alongside a lightweight, high-performance Ktor backend. The application delivers an interactive chess-playing experience across Android, iOS, and Desktop platforms with synchronous real-time game state synchronization.

---

## 2. Technical Special Aspects
- **Custom Build Logic Conventions**: Utilizes modular Gradle convention plugins (`build-logic/conventions`) written in Kotlin DSL to standardize dependencies, target configurations, and build tasks across multi-module KMP subprojects.
- **Bi-Directional WebSocket Protocol**: Implements custom type-safe event serializers (`ClientToServerBaseEvent` and `ServerToClientBaseEvent`) built on top of `kotlinx.serialization` for low-latency WebSocket communication.
- **Adaptive Screen Layout Engine**: Dynamically adapts UI representation based on active display dimensions (`PhonePortraitGameScreen`, `PhoneLandscapeGameScreen`, `DesktopGameScreen`) with custom board cell modifiers.
- **Shared Game Domain Engine**: Centralized move validation, turn state tracking (`UiBoard`, `UiTurn`, `GameState`), piece placement encodings, and timer section components shared across target platforms via `chessk-common`.

---

## 3. Technologies Used
- **Languages**: Kotlin (100% shared business & UI logic)
- **Frontend Architecture**: Kotlin Multiplatform (KMP), Compose Multiplatform
- **Backend Architecture**: Spring Boot, Ktor Server, Ktor WebSockets, Kotlin Coroutines
- **State Management & Navigation**: Navigation3, MVVM
- **Serialization**: `kotlinx.serialization` (JSON & WebSocket frame payloads)
- **Build Tools**: Gradle KTS, Gradle Version Catalogs (`libs.versions.toml`, `server.versions.toml`)

---

## 4. Testing Technologies
- **Unit & Logic Testing**: `kotlin.test` framework across multiplatform target modules
- **Target Test Suites**:
  - `commonTest`: Shared board state algorithms and move serialization validation
  - `iosTest`: iOS-specific runtime integration tests
  - `androidHostTest`: Android host environment test runner
  - `jvmTest`: Desktop/JVM test execution runner

---

## 5. Cloud Technologies
- **Deployment Readiness**: Standard Gradle shadow JAR distribution for Ktor server backend. Cloud hosting ready for containerized platforms (Docker, AWS ECS, GCP Cloud Run).

---

## 6. ROADMAP
- [ ] Integrate an AI chess engine bot (Stockfish API / localized engine) for offline single-player mode.
- [ ] Implement user authentication, ELO rating calculation, and competitive matchmaking pools.
- [ ] Add PGN (Portable Game Notation) export and full game move replay history.
- [ ] Implement spectator mode allowing active match streams and live chat rooms.
