# 🚦 Smart Traffic Control System

A Java-based simulation of an intelligent fine management system tailored for **Prince Sultan University (PSU)**. Inspired by the **Saher system** implemented across Saudi Arabia, this system aims to optimize traffic enforcement within PSU premises.

---

## 📖 Project Overview

With Riyadh's rapid growth due to Vision 2030 initiatives, traffic congestion and violations have become a serious issue — even within PSU. Currently, PSU enforces parking fines without a well-defined or transparent system. This project proposes an intelligent traffic fine system that:

- Automates fine calculation based on multiple conditions.
- Incorporates PSU's academic calendar and event types.
- Offers alternative penalties (e.g., PSU service hours).
- Applies progressive penalties for repeat offenders.
- Ensures every fine is VAT-inclusive.

---

## 🎯 Objectives

- 📌 Enforce traffic rules consistently within PSU.
- 📌 Help PSU guards by automating the fine process.
- 📌 Raise awareness and promote safer driving practices.
- 📌 Leverage collected fines to support PSU development.

---

## 🛠️ Features

- Automatic calculation of fines based on:
  - Day type (event, teaching, non-teaching, weekends)
  - Violation severity (Low, Medium, High)
  - Offender history
  - VAT (15%)
- Tracks both financial penalties and PSU service hours.
- Processes new/unprocessed fines and appends them to the log.
- Simulates Saher-like logic customized for PSU's environment.

---

## 📁 File Structure

The system works by reading and writing to three key text files:

### 1. `FineBase.txt` – Base Violation Definitions

| Field           | Description                                                                 |
|----------------|-----------------------------------------------------------------------------|
| violation code | 3-letter identifier (e.g., `SPD`, `BLK`)                                    |
| description    | Full explanation of the violation                                            |
| degree         | `Low`, `Medium`, `High`                                                     |
| base amount    | Initial fine amount (before modifiers)                                      |
| double %       | Whether fine can increase with history (0 = No, >0 = Yes, % value)          |
| hours          | PSU service hours if opted                                                 |
| both           | `Yes` for High violations (must pay + serve hours), `No` otherwise          |

---

### 2. `ProcessedFines.txt` – Already Logged Fines


- Includes all completed fines
- Used to determine repeat violations

---

### 3. `UnprocessedFines.txt` – New Violations to Process


| Day Type | Description                                 |
|----------|---------------------------------------------|
| 1        | Final exams or major PSU events             |
| 2        | Regular PSU teaching days                   |
| 3        | Student breaks (non-teaching)               |
| 4        | Weekends or non-PSU working hours           |

---

## 🧠 Processing Logic

The program reads `UnprocessedFines.txt` and processes each violation based on:

### 🎯 Violation Scenarios:

1. **Final Exams or Events (Type 1)**:
   - Fine amount is **doubled**
   - PSU service hours **also doubled**

2. **Regular Teaching Days (Type 2)**:
   - Normal fines and hours applied

3. **Non-Teaching Days (Type 3)**:
   - Fine only; **no PSU service hours**
   - High-degree violations are **not excluded**

4. **Weekends/Off Hours (Type 4)**:
   - **Low/Medium**: fine and hours = 0 (only logged)
   - **High**: processed as regular violations

5. **Repeat Offenders**:
   - If driver ID exists in `ProcessedFines.txt`, apply **10% penalty increase**

6. **VAT**:
   - Final fine amount is subject to **15% VAT**

---

## 📦 Output

Newly processed violations are **appended** to `ProcessedFines.txt` in this format:


All entries will have `completed = yes`.

---

## 💡 Assumptions

- All violators are **PSUers**
- Driver ID is found from previous processed fines
- Location is not currently considered in fine logic
- Once processed, fines are marked as **completed**

---

## 🧪 How to Run

1. Ensure all input files (`FineBase.txt`, `UnprocessedFines.txt`, `ProcessedFines.txt`) exist in the project root.
2. Open the project in **IntelliJ IDEA**
3. Run the `Main.java` class
4. Processed fines will be **appended** to `ProcessedFines.txt`

---

## 🔧 Tech Stack

- 💻 Language: Java
- 🧠 IDE: IntelliJ IDEA
- 📄 File Handling: Standard Java IO

---

