# FoodDeliveryApp

**End-to-end microservice for FoodDeliveryApp**

This is a full-featured Food Delivery Application consisting of multiple microservices to power a food ordering system — covering user management, food catalog, restaurant listings, orders, and more.

---

## Architecture & Services

The app is structured as microservices. Here are some of the key modules:

- **food-delivery-app**: Angular frontend application for the food delivery system.
- **eureka**: Service discovery and registration server.
- **foodcatalogue**: Service to manage and list different food items.
- **order**: Handles order creation, tracking, and order lifecycle.
- **restaurantlisting**: Service to manage restaurant profiles and listings.
- **userinfo**: Manages user profiles, authentication, and profiles.

Each component/service is designed to be independently deployable.

---

## Features

- User registration and authentication  
- Restaurant listing & browsing  
- Food catalogue browsing  
- Create & manage orders  
- Microservices architecture for scalability  

---

## Tech Stack

### Backend
- **Language:** Java 21  
- **Framework:** Spring Boot 3.3.5  
- **Service Discovery:** Eureka Server
- **Cloud:** Spring Cloud 2023.0.3
- **Database:** MySQL (Restaurant, Food Catalogue, User) & MongoDB (Orders)  
- **API:** REST  

### Frontend
- **Framework:** Angular 17
- **Language:** TypeScript
- **Styling:** CSS3 with modern gradients
- **HTTP Client:** Angular HttpClient
- **Routing:** Angular Router

---

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.x
- Node.js 18+ and npm
- MySQL Server
- MongoDB Server

### 1. Clone the repository

```bash
git clone https://github.com/patelajay005/FoodDeliveryApp.git
cd FoodDeliveryApp
