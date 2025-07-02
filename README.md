# 🛒 TasteHub – Food Delivery E-Commerce System

**TasteHub** is a full-stack food delivery e-commerce platform that allows customers to browse meals, place orders, track delivery progress, and manage their cart. 
The system supports both customer and admin roles, each with distinct functionalities.

---

## Technologies Used

- **Frontend**: Angular 15 (CLI: 15.2.11, Core: 15.2.10)
- **Backend**: Spring Boot 3
- **Database**: MySQL
- **Authentication**: Spring Security with OAuth2
- **Payment Options (UI only)**: PayPal, Credit/Debit Card, Cash on Delivery *(no backend processing yet)*
- **Others**: HTML, CSS, TypeScript
- **Testing Tool**: Postman

---

## Environment Details

- **Angular CLI**: 15.2.11  
- **Angular Core**: 15.2.10  
- **Node.js**: 17.9.1 ⚠️ *(Not officially supported – recommended: Node.js 18.x or 20.x LTS)*  
- **npm**: 8.11.0  
- **Operating System**: Windows 64-bit

---

## Key Features

### Customer Features
- **Register & Login**: Secure login with OAuth2-based authentication.
- **Product Browsing**: View food by category with dynamic search.
- **Cart Management**:
  - Add items to cart (requires login/signup).
  - Adjust quantity using `+` and `–` buttons.
  - Delete unwanted items from the cart.
- **Checkout**:
  - Fill in delivery address.
  - Select preferred payment method (e.g. PayPal, credit/debit card, or cash on delivery).
  - ⚠️ *Note: Payment options are UI-only — actual processing is not implemented.*
- **Track Orders**:
  - Enter Order ID to check order status (e.g. Pending → Confirmed).
- **Logout**: Secure logout via navbar.

### Admin Features
- **Admin Login**: Secure admin access via the same login page.
- **Product Management**: Add, update, or delete food items.
- **Order Management**:
  - View and confirm customer orders.
  - Once confirmed, the customer's order status updates in real-time.
- **System Control**: Full access to manage the product catalog and order flow.

---

## Project Structure Overview

```text
frontend/      --> Angular client (TasteHub UI)
backend/       --> Spring Boot REST API
database/      --> MySQL schema & seed data
postman/       --> Collection for API testing
