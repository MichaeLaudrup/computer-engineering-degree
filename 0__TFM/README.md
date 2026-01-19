# 🥗 NutreMe - Your Personalized Nutrition Assistant

Welcome to **NutreMe**, a state-of-the-art full-stack web application designed to help you achieve your nutritional goals through personalized diet tracking, meal recommendations, and intelligent data analysis.

---

## 🎯 Vision

NutreMe is more than just a calorie counter. It aims to provide a holistic view of your health by:
- 📊 **Dynamic Calculation**: Post-calculated nutritional targets based on physiological data.
- 💡 **Smart Engine**: Offering a smart recommendation engine for meals.
- 📈 **Visual Tracking**: Providing a visual dashboard to track your progress in real-time.
- 🥫 **Store Room**: Managing a "Store Room" of foods with detailed nutritional profiles.

---

## ✨ Key Features

### 🔐 Authentication & Profile
- **Security**: Secure Login/Sign-Up system using **JWT**.
- **Biometry**: Detailed physiological profile (Age, Weight, Height, Gender, Activity Level).
- **Goal Oriented**: Nutritional target selection (e.g., Lean Muscle Gain, Weight Loss).

### 📊 Dashboard & Tracking
- **Interactive Visualizations**: Real-time charts using **Chart.js** and **Highcharts** to monitor macros and progress.
- **Feedback Loop**: Daily progress tracking with visual feedback on calorie and nutrient intake.

### 🍎 Diet Management
- **Daily Register**: Log your daily food intake across different meal times (Breakfast, Lunch, Dinner, etc.).
- **Planner**: Organize and plan your nutrition throughout the week.

### 🧠 Smart Recommendation Engine
- **Rule-Based AI**: Powered by `json-rules-engine`, the backend provides intelligent food recommendations based on your unique profile and goals.

---

## 🛠️ Tech Stack

### Frontend 🎨
- **Core**: Angular 14
- **State**: NgRx (Store, Effects, DevTools)
- **UI**: Bootstrap 5 & PrimeNG
- **Charts**: Chart.js & Highcharts

### Backend ⚙️
- **Runtime**: Node.js & Express (TypeScript)
- **Database**: MongoDB with Mongoose ODM
- **Logic**: JSON Rules Engine
- **Auth**: JWT & BcryptJS

---

## 📂 Project Structure

```bash
NutriApp/
├── NutreMeApp_BackEnd/    # ✨ Express API (TypeScript)
├── NutreMeApp_FronEnd/    # ✨ Angular Application
├── 0__Memoria/            # 📝 Project documentation (Thesis)
└── 1__Origen_Datos/       # 📊 Raw data sources
```

---

## ⚙️ Getting Started

### 📋 Prerequisites
- **Node.js**: v14+ recommended
- **MongoDB**: Local instance or MongoDB Atlas

### 🛠️ Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/MichaeLaudrup/NutriApp.git
   cd NutriApp
   ```

2. **Backend Setup**
   ```bash
   cd NutreMeApp_BackEnd
   npm install
   npm run dev
   ```

3. **Frontend Setup**
   ```bash
   cd ../NutreMeApp_FronEnd
   npm install
   ng serve
   ```

4. **Access**
   Open `http://localhost:4200` in your browser.

---

## 👨‍💻 Author

**Michael Laudrup Luis González**
- [GitHub Profile](https://github.com/MichaeLaudrup)

---

> *Note: This project was developed as an **End of Degree Work (TFM)**, representing a comprehensive personal effort to build a serious, production-ready nutrition platform.*

