# Setup Guide for Food Delivery App Frontend

## Quick Start

Follow these steps to get the Angular frontend up and running:

### Step 1: Install Node.js and npm

Ensure you have Node.js (v18+) and npm installed:

```bash
node --version
npm --version
```

If not installed, download from [nodejs.org](https://nodejs.org/)

### Step 2: Install Angular CLI

```bash
npm install -g @angular/cli@17
```

Verify installation:

```bash
ng version
```

### Step 3: Install Project Dependencies

Navigate to the food-delivery-app directory and install dependencies:

```bash
cd food-delivery-app
npm install
```

### Step 4: Verify Backend Services

Before starting the frontend, ensure all backend microservices are running:

1. **Eureka Server** - http://localhost:9876
2. **Restaurant Service** - http://localhost:9091
3. **Food Catalogue Service** - http://localhost:9093
4. **Order Service** - http://localhost:9094

You can verify by visiting the Eureka dashboard at http://localhost:9876

### Step 5: Start the Frontend Application

```bash
npm start
```

Or use Angular CLI directly:

```bash
ng serve
```

The application will be available at: **http://localhost:4200**

### Step 6: Open in Browser

Navigate to http://localhost:4200 in your web browser.

## Application Flow

1. **Home Page** → Displays all restaurants
2. **Select Restaurant** → Click "View Menu" to see food items
3. **Add to Cart** → Select items and quantities
4. **View Cart** → Review order and adjust quantities
5. **Place Order** → Confirm and submit order

## Troubleshooting

### CORS Issues

If you encounter CORS errors, add the following to your backend Spring Boot services:

```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*");
            }
        };
    }
}
```

### Port Already in Use

If port 4200 is already in use, run on a different port:

```bash
ng serve --port 4201
```

### Backend Not Responding

1. Check if all microservices are registered with Eureka
2. Verify the services are running on correct ports
3. Check console logs for API errors
4. Ensure databases are running (MySQL for Restaurant & Food Catalogue, MongoDB for Orders)

### Clear Cache

If you face any build issues:

```bash
rm -rf node_modules package-lock.json
npm install
ng serve
```

## Development Mode

For development with live reload:

```bash
ng serve --open
```

This will automatically open the browser and reload on file changes.

## Production Build

To create an optimized production build:

```bash
ng build --configuration production
```

The build files will be in the `dist/food-delivery-app` directory.

## Testing

Run unit tests:

```bash
ng test
```

Run end-to-end tests:

```bash
ng e2e
```

## Code Formatting

To format code:

```bash
npm run format
```

## Environment Configuration

For different environments, you can create environment files:

- `src/environments/environment.ts` - Development
- `src/environments/environment.prod.ts` - Production

## Additional Resources

- [Angular Documentation](https://angular.io/docs)
- [TypeScript Documentation](https://www.typescriptlang.org/docs/)
- [RxJS Documentation](https://rxjs.dev/)

## Support

For issues or questions:
1. Check the main README.md
2. Review backend service logs
3. Check browser console for errors
4. Verify API endpoints are accessible

Happy coding! 🚀

