# ⚡ Electricity Billing System (Java + Spring Boot + Razorpay)

A full-stack electricity billing web application built using **Spring Boot**, **JSP/Thymeleaf**, **MySQL**, and **Razorpay** integration.  
Role-based system with clean dashboards, dynamic billing, and online payment capabilities.

---

## ✨ Features

### 🔐 Authentication
- Session-based login system
- Two roles: **Admin 👨‍💼** and **User 👩‍💻**
- Redirect to separate dashboards post login

### 🛠️ Admin Functionalities
- Add new users
- Generate monthly bills (units + amount)
- View all user details and bill status
- Track Paid ✅ / Unpaid ❌ bills

### 👤 User Functionalities
- View personal electricity bills
- See units, amount, and status
- One-click **Pay Now** with Razorpay
- Status auto-updates to “PAID” after payment

### 💳 Razorpay Integration
- Secure, real-time payments
- Dynamic amount passed via Thymeleaf
- Success redirect to user dashboard

---

## 🧠 Tech Stack

| Layer        | Tech Used                        |
|--------------|----------------------------------|
| Backend      | Java 17, Spring Boot             |
| Frontend     | JSP / Thymeleaf, HTML, CSS, JS   |
| Database     | MySQL                            |
| Payment      | Razorpay Java SDK                |
| UI Styling   | Bootstrap 5                      |
| Tools        | Postman (for API testing)        |

---

## 📸 Screenshots

1. 🛡️ Login & Register page  
2. 🧑‍💼 Admin dashboard (Add Bill / View Users)  
3. 👩‍💻 User dashboard with bill table + Pay Now  
4. 💳 Razorpay popup

---

## 🚀 Setup Instructions

1. Clone the repo  
2. Create MySQL DB named `electricity_db`  
3. Update `application.properties` with your DB credentials and Razorpay keys  
4. Run the project using:

```bash
mvn spring-boot:run
