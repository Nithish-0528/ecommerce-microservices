# E-Commerce Microservices Application

A full-stack e-commerce application built using **React.js, Spring Boot, Microservices, PostgreSQL, REST APIs, OpenFeign, and Apache Kafka**.

This project is being developed to understand how a real-world e-commerce application works from frontend to backend, including product management, cart management, checkout, order creation, inter-service communication, Kafka events, and stock management.

---

## Project Overview

The application follows a **microservices architecture** where different business responsibilities are handled by independent Spring Boot services.

### Main Application Flow

```text
Customer
   ↓
React Frontend
   ↓
Product Service
   ↓
Shopping Cart
   ↓
Checkout
   ↓
Order Service
   ↓
User Service / Product Service
   ↓
PostgreSQL
   ↓
Kafka
   ↓
Stock Reduction
   ↓
Order Confirmation
```

---

# Architecture

```text
                         React Frontend
                               |
              +----------------+----------------+
              |                |                |
              ↓                ↓                ↓
        User Service    Product Service    Order Service
              |                |                |
              ↓                ↓                ↓
           user_db         product_db        order_db
                               ↑
                               |
                            Kafka
                               |
                               |
                         Order Created
                            Event
```

---

# Technologies Used

## Frontend

* React.js
* JavaScript
* HTML
* CSS
* React Router
* React Context API
* Fetch API
* Vite

## Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* REST APIs
* OpenFeign
* Bean Validation
* Global Exception Handling
* Apache Kafka

## Database

* PostgreSQL

Separate databases are maintained for different microservices:

```text
user_db
product_db
order_db
```

## Tools

* Eclipse
* Visual Studio Code
* Postman
* Git
* GitHub
* Jira
* Apache Kafka

---

# Project Structure

```text
e-commerce/
│
├── frontend/
│   │
│   ├── src/
│   │   ├── components/
│   │   │   ├── product/
│   │   │   │   ├── ProductList.jsx
│   │   │   │   ├── ProductCard.jsx
│   │   │   │   ├── ProductDetails.jsx
│   │   │   │   └── ProductDetails.css
│   │   │   │
│   │   │   ├── cart/
│   │   │   │   ├── Cart.jsx
│   │   │   │   └── Cart.css
│   │   │   │
│   │   │   ├── checkout/
│   │   │   │   ├── Checkout.jsx
│   │   │   │   └── Checkout.css
│   │   │   │
│   │   │   └── order/
│   │   │       ├── OrderList.jsx
│   │   │       ├── OrderSuccess.jsx
│   │   │       └── OrderList.css
│   │   │
│   │   ├── context/
│   │   │   └── CartContext.jsx
│   │   │
│   │   ├── services/
│   │   │   ├── productService.js
│   │   │   ├── userService.js
│   │   │   └── orderService.js
│   │   │
│   │   └── App.jsx
│   │
│   └── package.json
│
├── user-service/
│
├── product-service/
│
├── order-service/
│
└── README.md
```

> Folder names can be changed according to the actual project structure.

---

# Frontend

The frontend is developed using React.js and communicates with backend microservices through REST APIs.

---

## Product List

The Product List retrieves products from the Product Service.

```text
ProductList
     ↓
productService
     ↓
GET /products
     ↓
Product Service
     ↓
PostgreSQL
```

Products are displayed using reusable `ProductCard` components.

### Product List Features

* Display products
* Product name
* Product description
* Product price
* Product quantity
* View Product button
* Product search

---

# Product Details

Users can select a product and navigate to:

```text
/products/:id
```

React Router's `useParams()` is used to retrieve the product ID.

```text
Product List
     ↓
View Product
     ↓
/products/4
     ↓
ProductDetails
     ↓
GET /products/4
     ↓
Product Service
```

The Product Details page displays:

* Product name
* Description
* Price
* Stock
* Category
* Rating
* Product features
* Add to Cart button

---

# Shopping Cart

The application uses **React Context API** for shared cart state.

The cart state is maintained by `CartProvider`.

```text
                    CartProvider
                         |
                    cartItems
                   /         \
                  /           \
       ProductDetails          Cart
              |                  |
         addToCart        quantity update
                               |
                         remove product
```

### Cart Features

* Add product to cart
* Increase quantity
* Decrease quantity
* Remove product
* Calculate subtotal
* Calculate total
* Clear cart after successful order

---

# Cart Context

The `CartContext` provides shared cart functionality.

Main functions:

```text
addToCart()
increaseQuantity()
decreaseQuantity()
removeFromCart()
clearCart()
```

Using Context API avoids passing cart information through multiple components using props.

---

# React Routing

React Router is used for navigation between application pages.

Current routes:

```text
/products
/products/:id
/cart
/checkout
/order-success
```

Example:

```text
/products
     ↓
ProductList

/products/4
     ↓
ProductDetails

/cart
     ↓
Cart

/checkout
     ↓
Checkout

/order-success
     ↓
OrderSuccess
```

---

# Checkout

Checkout represents the stage where the customer prepares the order before placing it.

The checkout page contains:

## Delivery Address

```text
Name
Phone
Address
City
State
Pincode
```

## Payment Method

Currently supported payment selections:

```text
COD
UPI
CARD
```

The selected payment method is maintained using React state.

> Actual payment gateway integration will be implemented as a future feature.

---

# Place Order

When the customer clicks **Place Order**, the frontend creates an `OrderRequest`.

Example:

```json
{
  "userId": 1,
  "items": [
    {
      "productId": 4,
      "quantity": 2
    },
    {
      "productId": 7,
      "quantity": 1
    }
  ],
  "deliveryAddress": {
    "name": "Nithish",
    "phone": "9876543210",
    "address": "Electronic City",
    "city": "Bengaluru",
    "state": "Karnataka",
    "pincode": "560100"
  },
  "paymentMethod": "UPI"
}
```

The request is sent using the browser's Fetch API.

```text
Checkout
   ↓
Create OrderRequest
   ↓
fetch()
   ↓
POST /orders
   ↓
Order Service
```

---

# Order Success

After the backend successfully creates the order:

```text
Place Order
     ↓
Order Service
     ↓
Order Created
     ↓
Clear Cart
     ↓
Navigate to /order-success
     ↓
Order Confirmation
```

The created order is passed to the Order Success page using React Router navigation state.

```javascript
navigate("/order-success", {
    state: {
        order: order
    }
});
```

The Order Success page can display:

* Order ID
* Payment method
* Order status
* Confirmation message
* Continue Shopping option

---

# Order List

The application includes an Order List UI for displaying created orders.

The frontend calls:

```text
getAllOrders()
```

to retrieve orders from the Order Service.

The Order List displays:

* Order ID
* Customer name
* City
* Payment method
* Total price
* Order status

Example:

```text
My Orders

+-------------------------------------------+
| Order #101                       CREATED   |
|                                           |
| Customer: Nithish                         |
| Location: Bengaluru                       |
| Payment: UPI                               |
| Total: ₹99.98                              |
+-------------------------------------------+
```

---

# Backend Microservices

The backend is divided into independent Spring Boot microservices.

---

# User Service

The User Service manages user-related information.

### Responsibilities

* User management
* User data storage
* Provide user information to other services

Database:

```text
user_db
```

---

# Product Service

The Product Service manages product-related operations.

### Responsibilities

* Create products
* Retrieve products
* Retrieve product by ID
* Search products
* Manage product stock
* Reduce product stock

Database:

```text
product_db
```

### Product APIs

```text
GET /products
```

Retrieve products.

```text
GET /products/{id}
```

Retrieve a product by ID.

```text
GET /products?searchName=
```

Search products by name.

The product search uses case-insensitive name matching.

---

# Order Service

The Order Service manages order-related operations.

### Responsibilities

* Create orders
* Validate order requests
* Communicate with User Service
* Communicate with Product Service
* Store orders
* Retrieve orders
* Publish order events

Database:

```text
order_db
```

---

# Order Entity

The current Order entity contains:

```text
id
userId
name
city
paymentMethod
totalPrice
status
```

Conceptually:

```text
Order
 |
 +-- id
 +-- userId
 +-- name
 +-- city
 +-- paymentMethod
 +-- totalPrice
 +-- status
```

---

# Microservice Communication

The Order Service communicates with other microservices using REST APIs and OpenFeign.

### Order → User

```text
Order Service
      |
      ↓
UserClient
      |
      ↓
User Service
      |
      ↓
user_db
```

### Order → Product

```text
Order Service
      |
      ↓
ProductClient
      |
      ↓
Product Service
      |
      ↓
product_db
```

The Order Service does not directly access the databases owned by other services.

---

# Kafka Integration

Apache Kafka is used for asynchronous event communication.

After an order is created, an order-created event can be published.

```text
Order Service
      ↓
Order Created Event
      ↓
Kafka Topic
      ↓
Product Service
      ↓
Stock Reduction
```

The project uses an event such as:

```text
OrderCreatedEvent
```

This demonstrates **event-driven communication** between microservices.

---

# Stock Management

Stock is reduced when products are successfully ordered.

Example:

```text
Initial Stock = 10

Ordered Quantity = 2

Remaining Stock = 8
```

The product stock update can use a conditional query:

```sql
UPDATE product
SET stock = stock - 1
WHERE id = 101
AND stock > 0;
```

The `stock > 0` condition helps prevent stock from becoming negative when stock is unavailable.

---

# Database Architecture

Each microservice owns its own database.

```text
User Service
     ↓
user_db

Product Service
     ↓
product_db

Order Service
     ↓
order_db
```

The services communicate through APIs rather than directly accessing another service's database.

This follows the **database-per-service** approach used in microservice architectures.

---

# Complete Order Flow

The current end-to-end order flow is:

```text
                    Customer
                       |
                       ↓
                 Product List
                       |
                       ↓
                Product Details
                       |
                       ↓
                  Add to Cart
                       |
                       ↓
                     Cart
                       |
                       ↓
                   Checkout
                       |
              +--------+--------+
              |                 |
              ↓                 ↓
       Delivery Address   Payment Method
              |                 |
              +--------+--------+
                       |
                       ↓
                  Place Order
                       |
                       ↓
                 Order Service
                       |
              +--------+--------+
              |                 |
              ↓                 ↓
        User Service      Product Service
              |                 |
              ↓                 ↓
          User Data       Product Data
                                |
                                ↓
                         Stock Validation
                                |
                                ↓
                          Stock Reduction
                                |
                                ↓
                              Kafka
                                |
                                ↓
                         Order Processing
                                |
                                ↓
                           order_db
                                |
                                ↓
                         Order Response
                                |
                                ↓
                           Clear Cart
                                |
                                ↓
                       Order Success Page
```

---

# Error Handling

The backend includes global exception handling using Spring's exception handling mechanisms.

The purpose is to provide consistent error responses instead of handling every exception separately inside each controller.

Example:

```text
Request
   ↓
Controller
   ↓
Service
   ↓
Exception
   ↓
Global Exception Handler
   ↓
Error Response
```

---

# Current Authentication Status

Authentication and authorization are not yet implemented.

Currently, the order request uses a temporary user ID:

```text
userId = 1
```

This is for development and testing.

A future authentication flow will be:

```text
Login
  ↓
JWT
  ↓
JWT Filter
  ↓
Authenticated User
  ↓
Create Order
```

The hard-coded user ID will be replaced with the authenticated user's ID.

---

# API Testing

Backend APIs can be tested using Postman.

Example:

```http
POST /orders
Content-Type: application/json
```

Request body:

```json
{
  "userId": 1,
  "items": [
    {
      "productId": 4,
      "quantity": 2
    }
  ],
  "deliveryAddress": {
    "name": "Nithish",
    "phone": "9876543210",
    "address": "Electronic City",
    "city": "Bengaluru",
    "state": "Karnataka",
    "pincode": "560100"
  },
  "paymentMethod": "UPI"
}
```

---

# Running the Application

## 1. Start PostgreSQL

Make sure PostgreSQL is running.

Create the required databases:

```text
user_db
product_db
order_db
```

---

## 2. Start User Service

Run the Spring Boot User Service.

Make sure it can connect to:

```text
user_db
```

---

## 3. Start Product Service

Run the Spring Boot Product Service.

Make sure it can connect to:

```text
product_db
```

---

## 4. Start Order Service

Run the Spring Boot Order Service.

Make sure it can communicate with:

```text
User Service
Product Service
PostgreSQL
Kafka
```

---

## 5. Start Kafka

Start Kafka before testing Kafka-based order events.

The project uses Kafka for asynchronous communication between services.

---

## 6. Start React Frontend

Navigate to the frontend directory:

```bash
npm install
```

Then start the development server:

```bash
npm run dev
```

Open the URL provided by Vite in the terminal.

---

# Current Features

## Frontend

* Product listing
* Product search
* Product details
* React Router navigation
* Shopping cart
* Add to cart
* Increase quantity
* Decrease quantity
* Remove product
* Cart total calculation
* Checkout
* Delivery address
* Payment method selection
* Place order
* Order success page
* Order list
* Clear cart after successful order
* Responsive UI

## Backend

* User Service
* Product Service
* Order Service
* PostgreSQL
* REST APIs
* Product search
* Product retrieval
* Order creation
* Order retrieval
* Order database
* OpenFeign communication
* Kafka integration
* Order-created event
* Stock reduction
* Global exception handling
* Bean validation

---

# Future Development

## Authentication

* User registration
* Login
* Password encryption
* JWT authentication
* JWT filter
* Role-based authorization

## Orders

* Order details page
* My Orders for logged-in user
* Order status management
* Cancel order
* Order tracking

## Products

* Product categories
* Pagination
* Sorting
* Advanced search
* Product images

## Payments

* Payment gateway integration
* Payment verification
* Payment status
* Payment failure handling
* Idempotency

## Performance

* Database indexing
* Query optimization
* Pagination
* Redis caching
* Kafka-based asynchronous processing

## Microservices Infrastructure

* API Gateway
* Service Discovery
* Centralized configuration
* Distributed tracing
* Monitoring
* Docker
* Docker Compose

---

# Learning Objectives

This project is being developed to gain practical experience with a real-world full-stack microservices application.

### Frontend

```text
React
 ↓
Components
 ↓
State Management
 ↓
Context API
 ↓
React Router
 ↓
REST API
 ↓
Fetch
```

### Backend

```text
Spring Boot
 ↓
REST APIs
 ↓
DTOs
 ↓
Validation
 ↓
Service Layer
 ↓
JPA / Hibernate
 ↓
PostgreSQL
```

### Microservices

```text
Microservices
 ↓
OpenFeign
 ↓
Kafka
 ↓
Event-Driven Communication
 ↓
Database Per Service
```

---

# Project Status

**Status: In Development**

The core e-commerce purchase flow is currently working:

```text
Browse Products
      ↓
View Product
      ↓
Add to Cart
      ↓
Checkout
      ↓
Place Order
      ↓
Order Created
      ↓
Stock Reduced
      ↓
Order Confirmation
      ↓
Order List
```

The project is being developed incrementally to understand both the **business flow** and the **technical implementation** of a modern full-stack e-commerce application.
