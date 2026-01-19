# NutreMeApp - Backend 🔙

This is the backend API for the **NutreMe** application, built with **Node.js**, **Express**, and **TypeScript**. It provides the core business logic, authentication, and data persistence for the nutrition platform.

## 🚀 Features

- **JWT Authentication**: Secure user registration, login, and password management.
- **Personalized Logic**: A recommendation engine powered by `json-rules-engine` that adapts to user profiles.
- **RESTful API**: Structured endpoints for managing meals, user data, daily registers, and meal sections.
- **Mailing System**: Integration with **Nodemailer** for automated communication (e.g., password reset).
- **Data Initialization**: Scripts to populate the database from CSV sources (food composition data).

## 🛠 Tech Stack

- **Runtime**: Node.js
- **Architecture**: Express with TypeScript
- **Database**: MongoDB with Mongoose
- **Validation**: Validator.js
- **Environment**: Dotenv for configuration management
- **Containerization**: Docker & Docker Compose support included.

## 📦 API Endpoints

- `/api/v1/users`: Auth and user management.
- `/api/v1/meals`: Aliment database operations.
- `/api/v1/user-data`: Physiological profiles.
- `/api/v1/daily-meals-registers`: Daily intake tracking.
- `/api/v1/section-meals`: Categorized meal collections.
- `/api/v1/recommendations-engine`: Smart aliment suggestions.

## ⚙️ Development

### Configuration

Create or modify `config.env` in the root of the backend directory with the following variables:
- `SERVER_PORT_NUMBER`
- `NODE_ENV`
- `MONGODB_URL`
- `JWT_SECRET`
- `JWT_EXPIRES_IN`

### Commands

- `npm install`: Install dependencies.
- `npm run dev`: Start the server in development mode using `ts-node`.
- `npm run start:dev`: Start with `nodemon` for auto-reloading.
- `npm run build`: Compile TypeScript to JavaScript.

---

*Part of the NutreMe Project - End of Degree Work (TFG).*
