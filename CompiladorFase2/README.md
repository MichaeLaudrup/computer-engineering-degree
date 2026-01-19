# 🏗️ Compiler Implementation - Phase 2

Building upon the first phase, this project completes the compiler by adding **Semantic Analysis** and **Code Generation**.

---

## 🚀 Overview

This phase elevates the compiler from a simple parser to a functional tool that generates executable logic.
- 🧠 **Semantic Analysis**: Type checking, scope validation, and ensuring the meaning of the code is correct.
- 📜 **Code Generation**: Translating the validated parse tree into an assembly-like output or intermediate code.

## 🛠️ Stack

- **Language**: `Java`
- **Parser/Semantic Tools**: `CUP` (Construction of Useful Parsers)
- **Runtime**: JVM

## 📂 Key Resources

- `enunciadoPracticaPL2_2019_2020.pdf`: Original assignment specifications.
- `DirectricesImplementacionPracticaPL2_2019_2020.pdf`: Implementation guidelines.

---

## ⚙️ How to Run

1.  **Semantic Processing**:
    The compiler uses CUP to handle semantic actions during parsing.
2.  **Compilation**:
    ```bash
    javac -cp ".;lib/*" src/**/*.java
    ```
3.  **Testing**:
    Run the compiler against complex test cases to verify semantic correctness.

---

*Academic project for the "Procesadores de Lenguajes" course.*
