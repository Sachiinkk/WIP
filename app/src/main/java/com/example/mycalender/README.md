# 📆 Jetpack Compose Calendar App

A modern, smooth-scrolling calendar app built completely in **Jetpack Compose**, featuring:

- 📅 Month-wise calendar layout
- 🔴 Highlighted Sundays and holidays
- 📌 Holiday click-to-toast with names
- 🔐 Logout + Login Navigation
- 🎨 Dark theme support

---

## 📸 Preview

| Calendar View | Holiday Toast | Logout Screen |
|:-------------:|:-------------:|:-------------:|
| ![calendar](preview/calendar.png) | ![toast](preview/toast.png) | ![logout](preview/logout.png) |

---

## 🔧 Tech Stack

- **Jetpack Compose**
- **Kotlin**
- **Navigation Compose**
- **Material3**
- **LazyColumn**
- **Composable-based UI Architecture**

---

## 📁 Folder Structure

app/ ├── model/ │ ├── MonthModel.kt │ ├── MonthView.kt │ ├── DayItem.kt │ ├── HolidayData.kt │ └── CustomToolbar.kt ├── ui.theme/ ├── MainActivity.kt └── LogoutScreen.kt



---

## ✅ Features Implemented

- [x] **Month Calendar UI** using `LazyColumn`
- [x] **Chunked Rows** of days with headers
- [x] **Sunday + Holiday Highlighting** (in red)
- [x] **Clicking a holiday shows Toast** with name
- [x] **Smooth scrolling** using LazyColumn optimizations
- [x] **Navigation**: Logout & return to calendar
- [x] **Dark background** with theming
- [x] **Fixes for padding & day alignment**
- [x] **Holiday map integration** with over 30 Indian holidays

---

## 📚 Resources Used

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- [Material3 Compose](https://developer.android.com/jetpack/androidx/releases/compose-material3)
- [Compose Layouts & LazyColumn](https://developer.android.com/jetpack/compose/lists)

---

## 🚀 Getting Started

Make sure your `build.gradle.kts` has:
```kotlin
implementation("androidx.navigation:navigation-compose:2.7.6")
implementation("androidx.compose.material3:material3")
