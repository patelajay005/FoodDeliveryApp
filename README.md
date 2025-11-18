# FoodDeliveryApp

**End-to-end microservice for FoodDeliveryApp**

This is a full-featured Food Delivery Application consisting of multiple microservices to power a food ordering system — covering user management, food catalog, restaurant listings, orders, and more.

---

## 📦 Architecture & Services

The app is structured as microservices. Here are some of the key modules:

- **food-delivery-app**: The core service for managing food orders.  
- **foodcatalogue**: Service to manage and list different food items.  
- **order**: Handles order creation, tracking, and order lifecycle.  
- **restaurantlisting**: Service to manage restaurant profiles and listings.  
- **userinfo**: Manages user profiles, authentication, and profiles.

Each component/service is designed to be independently deployable.

---

## 🚀 Features

- User registration and authentication  
- Restaurant listing & browsing  
- Food catalogue browsing  
- Create & manage orders  
- Microservices architecture for scalability  
- (You can add more here, e.g., payment, notifications, admin panel, tracking)

---

## 🛠️ Tech Stack

- **Backend**: Java (or whatever language if different)  
- **Framework**: (Spring Boot / Micronaut / Quarkus / etc.)  
- **Database**: (MySQL / MongoDB / Postgres, mention what you're using)  
- **API**: REST APIs between services  
- **Communication**: (e.g., HTTP / gRPC / Message queues, based on your architecture)  
- **Authentication**: (JWT / Session-based / OAuth)  
- **Containerization (optional)**: Docker, Kubernetes (if used)

---

## 🔧 Getting Started (Development Setup)

1. **Clone the repo**

   ```bash
   git clone https://github.com/patelajay005/FoodDeliveryApp.git
   cd FoodDeliveryApp
