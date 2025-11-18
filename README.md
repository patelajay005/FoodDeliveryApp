# FoodDeliveryApp

**End-to-end microservice for FoodDeliveryApp**

This is a full-featured Food Delivery Application consisting of multiple microservices to power a food ordering system — covering user management, food catalog, restaurant listings, orders, and more.

---

## Architecture & Services

The app is structured as microservices. Here are some of the key modules:

- **food-delivery-app**: The core service for managing food orders.
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

- **Backend:** Java  
- **Framework:** Spring Boot  
- **Database:** MySQL  
- **API:** REST  

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/patelajay005/FoodDeliveryApp.git
cd FoodDeliveryApp
