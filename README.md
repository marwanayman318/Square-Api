# Square API Automation Testing Project

This project is an automated API testing framework built for validating multiple Square API modules, including **Payments, Refunds, Catalog, Checkout, and Customers**.  
The automation is implemented using **Java, TestNG, RestAssured, and Allure Reports**.

This repository demonstrates:
- Real API test automation
- Clean framework structure
- Positive & negative scenario coverage
- Modular test design
- Reusable service layer
- Data-driven test execution

---

## 🚀 Tech Stack

| Layer | Technology |
|------|------------|
| Programming Language | Java |
| Test Framework | TestNG |
| API Testing Library | RestAssured |
| Reporting | Allure |
| Build Tool | Maven |
| API Platform | Square API |
| Additional Tools | Postman (manual verification) |

---

## 📁 Project Structure
src
└── test
└── java
├── payments
│ ├── data
│ └── tests
├── refunds
│ ├── data
│ └── tests
├── catalog
│ ├── data
│ └── tests
├── checkout
│ ├── data
│ └── tests
├── customers
│ ├── data
│ └── tests
└── services


### 🔹 **services**
Contains reusable GET/POST request logic.

### 🔹 **module/tests**
Contains test scripts for each Square module.

### 🔹 **module/data**
Contains DataProviders supplying JSON bodies + test data.

---

## 🧪 Modules Covered

| Module | Endpoints Automated |
|--------|----------------------|
| Payments | Create Payment, Retrieve Payment |
| Refunds | Create Refund, Get Refund by ID, List Refunds |
| Catalog | Create Item, Retrieve Item, List Catalog, Delete Item |
| Checkout | Create Checkout Session |
| Customers | Create Customer, Retrieve Customer, List Customers |

---

## 📌 Why This Project?

The goal is to validate Square's API functionality through:

- Data-driven testing  
- Layered framework design  
- Positive and negative scenario validation  
- Reusable components  
- Integration with Allure reporting  

This ensures high reliability, early defect detection, and full coverage of essential functionalities.

---

## ▶️ How to Run the Tests

### 1️⃣ **Install Dependencies**

### 2️⃣ **Run TestNG Suite**

### 3️⃣ **Generate Allure Report**

---

## 📊 Example Allure Report Screenshots

(Add your screenshots here)

---

## 📝 Manual Testing (Postman)

Before automation, all endpoints were manually validated using **Postman**:

- Verified request/response shape  
- Checked success & error flows  
- Created collections per module  
- Used environment variables  
- Ensured APIs work before automation  

This ensured stable test coverage.

---

## 👨‍💻 Author

**Marwan Ayman**  
Automated API Testing Project – Graduation Deliverable

---

## 📘 Full Documentation

Full details available in:  
👉 **Documentation.md** in this repository


