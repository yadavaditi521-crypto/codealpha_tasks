# Student Grade Tracker

A robust, object-oriented Java console application designed to input, manage, and analyze student grades dynamically. This project utilizes custom class structures and dynamic data collections (`ArrayList`) to offer an intuitive user experience with bulletproof data validation.

## 🚀 Features

- **Dynamic Data Management**: Uses `ArrayList` to store records dynamically, allowing you to add unlimited students without sizing constraints.
- **Robust Input Validation**: Prevents application crashes from accidental invalid user inputs (e.g., text instead of numbers, grades outside the 0–100 range, empty names).
- **Comprehensive Summary Reports**: Displays a clean, tabular layout of all registered students with their current grades.
- **Automated Analytics & Insights**: Calculates critical performance metrics instantly:
  - Class Average Score
  - Highest Scoring Student
  - Lowest Scoring Student

---

## 📂 Project Structure

The project consists of two core source files:
1. `Student.java` - The data model representing an individual student (encapsulating name and grade with appropriate accessors and formatting).
2. `GradeTracker.java` - The main controller handling the interactive menu system, data mutations, calculation engines, and terminal input/output buffers.

---

## 🛠️ Requirements

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Operating System**: Windows, macOS, or Linux.

---

## 💻 How to Setup, Compile, and Run

Follow these simple steps to run the project via your system's terminal or command prompt:

### Step 1: Save the Files
Create a new directory (e.g., `GradeTrackerProject`) and save both `Student.java` and `GradeTracker.java` inside it. Ensure that casing matches exactly.

### Step 2: Open Command Prompt / Terminal
Navigate into the directory where you saved the files:
```bash
cd path/to/your/GradeTrackerProject