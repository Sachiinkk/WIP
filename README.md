# 🌤️ MyCalendar App

A modern Android app built with **Jetpack Compose** and **MVVM architecture**. It features:

- 📅 Calendar with monthly view
- 🌦️ Weather forecast (weekly + daily)
- 🔄 Reusable UI components
- 🔒 Logout screen
- ⏳ Loader between screens

---

## 🔧 Tech Stack

- Kotlin
- Jetpack Compose
- MVVM (ViewModel + StateFlow)
- Retrofit (API calls)
- WeatherStack API
- Navigation Component
- Coroutines

---

## 📁 Folder Structure

app/ ├── data/ # Data layer (models, remote API, repo) ├── di/ # Dependency Injection setup ├── domain/usecase/ # UseCases like GetWeatherUseCase ├── presentation/ │ ├── calendar/ # Calendar UI and ViewModel │ ├── weather/ # Weather screen and ViewModel │ └── component/ # Reusable UI like CustomToolbar, LogoutScreen ├── utils/ # Helper utils (holidays map, dialog) └── MainActivity.kt # App entry point + Navigation



---

## 🚀 Features

- 📆 Monthly calendar UI
- 🌤️ Weekly + detailed day forecast using WeatherStack API
- 👤 Custom toolbar with logout confirmation
- 🌀 Smooth loader before navigating to weather screen
- 🔙 Back navigation from weather to calendar
- 🔐 Logout screen with re-login option

---

## 🖼️ Screens

- **CalendarScreen** – Monthly calendar with date selection
- **WeatherScreen** – Shows daily + weekly weather
- **LogoutScreen** – "You are logged out" with re-login button

---

## 📲 API Integration

- Weather data fetched from:  
  `https://api.weatherstack.com/current?access_key=YOUR_KEY&query=CITY`

> Replace `YOUR_KEY` in `RetrofitInstance.kt`.

---

## 💡 How to Run

1. Clone the repo
2. Add your WeatherStack API key
3. Build and run in Android Studio

---

## 📌 Note

- Images like `sun.png`, `rain.png` must be placed in `res/drawable/`.
- Network permission & cleartext support added in `AndroidManifest.xml`.

---

## ✨ Contributions

Feel free to fork, improve, or raise issues. Let’s make it better!

---

