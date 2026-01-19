# NutreMeApp - Frontend 🥗

This is the frontend component of the **NutreMe** application, built with **Angular 14**. It provides a rich, interactive user interface for managing personal nutrition and tracking dietary goals.

## 🚀 Technical Highlights

- **State Management**: Implements a robust reactive architecture using **NgRx** (Store, Effects, Entities, and DevTools) to handle complex application states like user profiles, diet registers, and food lists.
- **Dynamic Visualization**: Uses **Chart.js** and **Highcharts** to turn raw nutritional data into meaningful visual progress reports.
- **Responsive Design**: Built with **Bootstrap 5** and **PrimeNG** for a sleek, modern, and mobile-responsive experience.
- **Animations**: Custom Angular animations for smooth transitions between views and interactive elements.

## 📦 Key Modules

- `Dashboard`: Data visualization and progress tracking.
- `Diet`: Daily meal logging and scheduling.
- `NutriForm`: Physiological data entry and goal setting.
- `StoreRoom`: Searchable database of food items and their nutritional profiles.

## 🛠 Development

### Setup

1. Install dependencies:
   ```bash
   npm install
   ```

2. Start the development server:
   ```bash
   ng serve
   ```
   Navigate to `http://localhost:4200/`.

### Commands

- `ng build`: Build the project (output in `dist/`).
- `ng test`: Run unit tests via Karma.
- `ng lint`: Run ESLint for code quality checks.

---

*Part of the NutreMe Project - End of Degree Work (TFG).*
