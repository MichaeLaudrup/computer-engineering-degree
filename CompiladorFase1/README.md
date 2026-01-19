# ⚙️ Compiler Implementation - Phase 1

This project focuses on the initial stages of building a compiler for a custom programming language, specifically the **Lexical Analysis (Scanner)** and **Syntactic Analysis (Parser)**.

---

## 🚀 Overview

In this phase, we transform raw source code into a structured intermediate representation.
- 🔍 **Lexical Analysis**: Breaking the source code into a stream of tokens.
- 📐 **Syntactic Analysis**: Verifying the token stream against the language's grammar rules and building a parse tree.

## 🛠️ Stack

- **Language**: `Java`
- **Lexer Generator**: `JFlex` (or similar Lex implementation)
- **Parser Generator**: `BYacc/J` (or similar Yacc implementation)

## 📂 Structure

- `src/`: Source code for the lexer and parser definitions.
- `lib/`: Necessary libraries for the generation and execution.
- `doc/`: Detailed documentation and Javadoc.

---

## ⚙️ How to Run

1.  **Generate Lexer & Parser**:
    Use the provided scripts or tools to process `.l` and `.y` files.
2.  **Compile**:
    ```bash
    javac -cp ".;lib/*" src/**/*.java
    ```
3.  **Execute**:
    Run the compiler with a sample source file.

---

*Academic project for the "Procesadores de Lenguajes" course.*
