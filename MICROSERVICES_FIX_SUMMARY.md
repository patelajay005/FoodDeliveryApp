# Microservices POM.XML Fix Summary

## Issue Description
All microservices in the FoodDeliveryApp were using an **unstable Spring Boot SNAPSHOT version** (4.0.0-SNAPSHOT) which caused:
- ❌ Dependency resolution failures
- ❌ SSL certificate errors when accessing snapshot repositories
- ❌ Build failures across all services

## Fixed Microservices

### 1. ✅ **foodcatalogue** - Food Catalogue Service (Port 9093)
### 2. ✅ **restaurantlisting** - Restaurant Listing Service (Port 9091)
### 3. ✅ **order** - Order Service (Port 9094)
### 4. ✅ **eureka** - Eureka Discovery Server (Port 9876)
### 5. ✅ **userinfo** - User Information Service

## Changes Made

For each microservice, the following changes were applied to `pom.xml`:

### 1. Updated Spring Boot Version
```xml
<!-- BEFORE -->
<version>4.0.0-SNAPSHOT</version>

<!-- AFTER -->
<version>3.3.5</version>
```

### 2. Updated Spring Cloud Version
```xml
<!-- BEFORE -->
<spring-cloud.version>2025.1.0-M4</spring-cloud.version>

<!-- AFTER -->
<spring-cloud.version>2023.0.3</spring-cloud.version>
```

### 3. Fixed Dependency Names
```xml
<!-- BEFORE -->
<artifactId>spring-boot-starter-webmvc</artifactId>

<!-- AFTER -->
<artifactId>spring-boot-starter-web</artifactId>
```

### 4. Consolidated Test Dependencies
```xml
<!-- BEFORE (Multiple test dependencies) -->
<artifactId>spring-boot-starter-data-jpa-test</artifactId>
<artifactId>spring-boot-starter-webmvc-test</artifactId>

<!-- AFTER (Single unified dependency) -->
<artifactId>spring-boot-starter-test</artifactId>
```

### 5. Removed Snapshot Repositories
Removed the entire `<repositories>` and `<pluginRepositories>` sections that pointed to Spring snapshot repositories.

## How to Build and Run

### Step 1: Clean and Build All Services

Navigate to each service directory and run:

```bash
cd foodcatalogue
mvn clean install
cd ..

cd restaurantlisting
mvn clean install
cd ..

cd order
mvn clean install
cd ..

cd eureka
mvn clean install
cd ..

cd userinfo
mvn clean install
cd ..
```

Or use a single command from the root directory:
```bash
mvn clean install -pl eureka,userinfo,restaurantlisting,foodcatalogue,order
```

### Step 2: Start Services in Order

**1. Start Eureka Server First:**
```bash
cd eureka
mvn spring-boot:run
```
Wait until you see: "Started Eureka in X seconds"
Access at: http://localhost:9876

**2. Start Microservices (in any order):**

```bash
# Terminal 2
cd restaurantlisting
mvn spring-boot:run

# Terminal 3
cd foodcatalogue
mvn spring-boot:run

# Terminal 4
cd order
mvn spring-boot:run

# Terminal 5 (Optional)
cd userinfo
mvn spring-boot:run
```

**3. Start Angular Frontend:**
```bash
cd food-delivery-app
npm install
npm start
```
Access at: http://localhost:4200

## Verification

### Check Eureka Dashboard
Visit http://localhost:9876 and verify all services are registered:
- RESTAURANT-SERVICE (9091)
- FOOD-CATALOGUE-SERVICE (9093)
- ORDER-SERVICE (9094)
- USER-SERVICE (if applicable)

### Test Endpoints

**Restaurant Service:**
```bash
curl http://localhost:9091/restaurant/fetchAllRestaurants
```

**Food Catalogue Service:**
```bash
curl http://localhost:9093/foodCatalogue/fetchRestaurantAndFoodItemsById/1
```

**Order Service:**
```bash
curl http://localhost:9094/order/saveOrder -X POST -H "Content-Type: application/json" -d '{...}'
```

## Technology Stack (After Fix)

| Component | Version |
|-----------|---------|
| Spring Boot | 3.3.5 (Stable) |
| Spring Cloud | 2023.0.3 (Stable) |
| Java | 21 |
| Maven | 3.x |
| Angular | 17 |

## Database Configuration

Ensure the following databases are running:

**MySQL (for Restaurant, Food Catalogue, User Info):**
- Host: localhost:3306
- Databases: `restaurant`, `foodcataloguedb`, `userdb`
- Username: root
- Password: root

**MongoDB (for Order Service):**
- Host: localhost:27017
- Database: `orderdb`

## Troubleshooting

### If Maven still shows errors:

1. **Clear Maven cache:**
```bash
mvn dependency:purge-local-repository
```

2. **Force update:**
```bash
mvn clean install -U
```

3. **Delete local repository cache:**
```bash
# Windows
rmdir /s /q %USERPROFILE%\.m2\repository\org\springframework\boot\spring-boot-starter-parent\4.0.0-SNAPSHOT

# Linux/Mac
rm -rf ~/.m2/repository/org/springframework/boot/spring-boot-starter-parent/4.0.0-SNAPSHOT
```

### If services don't register with Eureka:

1. Ensure Eureka is running first
2. Check `application.yml` in each service for correct Eureka URL
3. Wait 30-60 seconds for services to register

## Summary

✅ All 5 microservices now use **stable Spring Boot 3.3.5**
✅ Removed dependency on snapshot repositories
✅ Fixed incorrect dependency names
✅ Services should now build and run without errors
✅ Angular frontend is ready to connect to backend services

## Next Steps

1. Start all backend services
2. Verify registration in Eureka dashboard
3. Start Angular frontend
4. Test the complete application flow
5. Create sample data in databases if needed

---

**Note:** If you encounter any CORS issues when connecting the Angular frontend to backend services, you may need to add CORS configuration to your Spring Boot controllers or create a global CORS configuration.

