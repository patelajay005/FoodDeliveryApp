# Downgrade Project to Java 17 (Temporary Workaround)

⚠️ **Warning:** This is NOT recommended as Spring Boot 3.3.5 works best with Java 17+, but some features may require Java 21.

## If you must use Java 17, make these changes:

### Change in ALL pom.xml files:

In each microservice (eureka, restaurantlisting, foodcatalogue, order, userinfo):

```xml
<properties>
    <java.version>17</java.version>  <!-- Changed from 21 to 17 -->
    <spring-cloud.version>2023.0.3</spring-cloud.version>
</properties>
```

### Files to Update:
- eureka/pom.xml
- restaurantlisting/pom.xml
- foodcatalogue/pom.xml
- order/pom.xml
- userinfo/pom.xml

### Then run:
```bash
mvn clean install
```

### IntelliJ IDEA Configuration:
1. Press `Ctrl + Alt + Shift + S`
2. Go to `Project`
3. Set `SDK` to `17`
4. Set `Language level` to `17`
5. Click `OK`

