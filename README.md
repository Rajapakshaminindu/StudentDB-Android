# StudentDB Android App 📱

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-purple.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Android](https://img.shields.io/badge/Platform-Android%20(API%2024%2B)-green.svg?style=flat&logo=android)](https://developer.android.com)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![SQLite](https://img.shields.io/badge/Database-SQLite-003B57.svg?style=flat&logo=sqlite)](https://www.sqlite.org)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A modern Android application demonstrating user authentication and local SQLite database connectivity using **Kotlin** and **Jetpack Compose**. This app showcases best practices for mobile app development with a clean UI and robust database management.

## Features ✨

- **User Authentication**
  - User registration with validation
  - Secure login functionality
  - Password-based authentication

- **Database Management**
  - Local SQLite database for persistent data storage
  - CRUD operations for user data
  - Efficient data management

- **User Dashboard**
  - View user profile information
  - Display registered users
  - Manage user data seamlessly

- **Student Information Management**
  - Store and retrieve student details:
    - Name
    - Registration Number
    - Department
    - Email
    - Phone Number
    - Secure Password Storage

- **Modern UI**
  - Built with Jetpack Compose for declarative UI
  - Material Design 3 components
  - Responsive and intuitive interface

## Tech Stack 🛠️

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Database:** SQLite (Local)
- **Navigation:** Jetpack Navigation Compose
- **Minimum SDK:** 24
- **Target SDK:** 37
- **Build System:** Gradle with Kotlin DSL

## Project Structure 📁

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/androiddbconnectivity/
│   │   │   ├── MainActivity.kt          # Main activity entry point
│   │   │   ├── LoginScreen.kt           # Login UI screen
│   │   │   ├── RegisterScreen.kt        # Registration UI screen
│   │   │   ├── DashboardScreen.kt       # Dashboard/home screen
│   │   │   ├── User.kt                  # User data model
│   │   │   ├── DBHelper.kt              # SQLite database helper
│   │   │   └── ui/                      # UI components
│   │   └── res/                         # Resources (layouts, strings, etc.)
│   └── test/                            # Unit tests
├── build.gradle.kts                     # App-level build configuration
└── README.md                            # This file
```

## Database Schema 📊

### Users Table

| Column | Type | Description |
|--------|------|-------------|
| id | INTEGER (PK) | Unique user identifier (Auto-increment) |
| name | TEXT | User's full name |
| regNo | TEXT | Registration/Roll number |
| department | TEXT | Department affiliation |
| email | TEXT | Email address |
| password | TEXT | Hashed password |
| phone | TEXT | Contact number |

## Getting Started 🚀

### Prerequisites

- Android Studio (latest version)
- JDK 11 or higher
- Android SDK (API 24+)
- Gradle 8.0+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Rajapakshaminindu/StudentDB-Android.git
   cd StudentDB-Android
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the project folder and open it

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run on emulator or device**
   - Click "Run" in Android Studio or use:
   ```bash
   ./gradlew installDebug
   ```

## Usage 📖

1. **Launch the App**
   - The app opens with the Login screen

2. **Register a New Account**
   - Click on "Register" if you don't have an account
   - Fill in all required details (name, email, password, etc.)
   - Submit to create your account in the local database

3. **Login**
   - Enter your registered email and password
   - Access your dashboard upon successful authentication

4. **Dashboard**
   - View your profile information
   - See list of all registered users
   - Manage your account

## Dependencies 📦

Key dependencies used in this project:

- `androidx.compose.ui` - Jetpack Compose UI framework
- `androidx.compose.material3` - Material Design 3 components
- `androidx.navigation.compose` - Navigation in Compose
- `androidx.activity.compose` - Activity integration with Compose
- `androidx.lifecycle.runtime.ktx` - Lifecycle management
- `android.database.sqlite` - SQLite database (Built-in)

## Security Notes 🔒

⚠️ **Important:** This is a demonstration project. In production:
- Implement proper password hashing (bcrypt, scrypt)
- Use encrypted shared preferences for sensitive data
- Implement proper input validation and sanitization
- Never store plain-text passwords
- Use modern authentication methods (OAuth, etc.)
- Implement proper error handling and logging

## API Reference 🔌

### DBHelper Class

#### Main Methods

- `insertUser(user: User)` - Add a new user to database
- `getUser(email: String, password: String)` - Authenticate user
- `getAllUsers()` - Retrieve all registered users
- `updateUser(user: User)` - Update existing user data
- `deleteUser(id: Int)` - Remove user from database

## Screenshots 📸

*Add your app screenshots here*

## Contributing 🤝

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Troubleshooting 🔧

### Build Issues
- Clean and rebuild: `./gradlew clean build`
- Invalidate Android Studio cache: File → Invalidate Caches → Restart

### Database Issues
- App database is stored locally in device/emulator memory
- Clear app data to reset database: Settings → Apps → [App Name] → Storage → Clear Data

### Compose Compatibility
- Ensure Compose BOM version matches your Android SDK version
- Check `gradle/libs.versions.toml` for version compatibility

## License 📄

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author ✍️

Developed by **[Minindu Rajapaksha](https://github.com/Rajapakshaminindu)**.
Created as a demonstration of Android development with Kotlin and Jetpack Compose.

## Support 💬

For issues, questions, or suggestions:
- Open an issue on GitHub
- Create a discussion in the repository

## Roadmap 🗺️

Future enhancements:
- [ ] Add Firebase authentication
- [ ] Implement encrypted database
- [ ] Add profile picture upload
- [ ] Implement user profile editing
- [ ] Add data export functionality
- [ ] Implement offline-first sync

---

**Happy Coding! 🎉**
