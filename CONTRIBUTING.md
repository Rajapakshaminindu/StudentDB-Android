# Contributing to StudentDB Android

Thank you for your interest in contributing to **StudentDB Android**! We welcome contributions from developers of all skill levels.

---

## Code of Conduct

Please be respectful, constructive, and considerate when interacting with fellow contributors and maintainers.

---

## How Can I Contribute?

### 1. Reporting Bugs
- Check the [Issues tab](https://github.com/Rajapakshaminindu/StudentDB-Android/issues) to ensure the bug hasn't already been reported.
- Open a new issue including:
  - Clear title and detailed description
  - Steps to reproduce
  - Device/emulator details (Android OS version, screen resolution)
  - Logcat stack traces if available

### 2. Suggesting Features
- Open an issue with a feature request tag.
- Clearly describe the proposed feature and why it would be useful for the project.

### 3. Submitting Pull Requests
1. Fork the repository on GitHub.
2. Clone your fork locally:
   ```bash
   git clone https://github.com/Rajapakshaminindu/StudentDB-Android.git
   cd StudentDB-Android
   ```
3. Create a descriptive feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
4. Follow code conventions and write unit tests for any new utilities or logic.
5. Commit your changes with meaningful commit messages:
   ```bash
   git commit -m "feat: add support for student profile image upload"
   ```
6. Push to your branch and open a Pull Request against the `main` branch.

---

## Coding Standards

- **Language:** Kotlin (idiomatic Kotlin, avoiding unnecessary Java patterns).
- **UI:** Jetpack Compose with Material 3 design tokens.
- **Naming Conventions:**
  - PascalCase for Composables, Classes, and Interfaces (`LoginScreen`, `User`, `DBHelper`).
  - camelCase for functions and variables (`isValidEmail`, `currentUser`).
  - UPPER_SNAKE_CASE for constants (`DATABASE_NAME`, `TABLE_USERS`).
- **Testing:** Include unit tests under `app/src/test` for business logic and helpers.

---

## Running Tests

Before submitting a Pull Request, verify that all unit tests pass:

```bash
./gradlew testDebugUnitTest
```

Thank you for making StudentDB Android better! 🎉
