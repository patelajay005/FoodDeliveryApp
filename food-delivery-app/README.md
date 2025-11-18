# Food Delivery App - Angular Frontend

This is the frontend Angular application for the Food Delivery microservices system.

## Features

- **Restaurant Listing**: Browse all available restaurants with their details
- **Food Catalogue**: View food items from selected restaurants
- **Shopping Cart**: Add items to cart with quantity management
- **Order Management**: Review cart and place orders

## Prerequisites

- Node.js (v18 or higher)
- npm (v9 or higher)
- Angular CLI (v17 or higher)

## Installation

1. Navigate to the project directory:
```bash
cd food-delivery-app
```

2. Install dependencies:
```bash
npm install
```

## Running the Application

1. Make sure all backend microservices are running:
   - Restaurant Service (port 9091)
   - Food Catalogue Service (port 9093)
   - Order Service (port 9094)
   - Eureka Server (port 9876)

2. Start the Angular development server:
```bash
npm start
```

3. Open your browser and navigate to:
```
http://localhost:4200
```

## Build

To build the project for production:
```bash
npm run build
```

The build artifacts will be stored in the `dist/` directory.

## Project Structure

```
src/
├── app/
│   ├── components/
│   │   ├── restaurant-listing/    # Restaurant listing component
│   │   ├── food-catalogue/        # Food catalogue component
│   │   └── order/                 # Order/Cart component
│   ├── models/                    # Data models
│   │   ├── restaurant.model.ts
│   │   ├── food-item.model.ts
│   │   └── order.model.ts
│   ├── services/                  # Angular services
│   │   ├── restaurant.service.ts
│   │   ├── food-catalogue.service.ts
│   │   ├── order.service.ts
│   │   └── cart.service.ts
│   ├── app.component.*           # Root component
│   ├── app.module.ts             # Main module
│   └── app-routing.module.ts     # Routing configuration
├── assets/                        # Static assets
├── styles.css                     # Global styles
└── index.html                     # Main HTML file
```

## API Endpoints

The application connects to the following backend services:

- **Restaurant Service**: `http://localhost:9091/restaurant`
- **Food Catalogue Service**: `http://localhost:9093/foodCatalogue`
- **Order Service**: `http://localhost:9094/order`

## Features Overview

### 1. Restaurant Listing
- Displays all available restaurants
- Shows restaurant details (name, address, city, description)
- Navigate to restaurant menu

### 2. Food Catalogue
- View all food items for a selected restaurant
- Filter by vegetarian/non-vegetarian
- Add items to cart with quantity selection
- Real-time cart count display

### 3. Order Management
- Review cart items
- Update quantities or remove items
- View order summary with total amount
- Place order with backend integration
- Clear cart functionality

## Technologies Used

- Angular 17
- TypeScript
- RxJS for reactive programming
- HttpClient for API communication
- Angular Router for navigation
- CSS3 for styling with modern gradients and animations

## Styling

The application features a modern, responsive design with:
- Gradient backgrounds
- Card-based layouts
- Smooth animations and transitions
- Mobile-responsive design
- Custom buttons and form elements

## Development

To run tests:
```bash
npm test
```

To watch for changes:
```bash
npm run watch
```

## Troubleshooting

1. **CORS Issues**: Make sure backend services have CORS enabled for `http://localhost:4200`
2. **API Connection**: Verify all backend services are running on their respective ports
3. **Port Conflicts**: Change the port by running: `ng serve --port 4201`

## Future Enhancements

- User authentication and authorization
- Order history tracking
- Payment gateway integration
- Restaurant search and filters
- User profile management
- Real-time order tracking
- Reviews and ratings

## License

This project is part of the Food Delivery Microservices Application.

