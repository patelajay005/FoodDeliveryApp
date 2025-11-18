<!--
Guidance for AI coding agents working on the Food Delivery Angular frontend.
Keep this file short, concrete, and tied to code that actually exists in the repo.
-->

# Copilot / AI Agent Instructions — Food Delivery App (Angular)

Summary
- This repo is an Angular 17 frontend for a Food Delivery microservices system.
- Key directories: `src/app/components`, `src/app/services`, `src/app/models`.

Quick start (commands)
- Install dependencies: `npm install`
- Run dev server: `npm start` (runs `ng serve`, default port `4200`)
- Build for production: `npm run build`
- Run tests: `npm test`
- Watch build: `npm run watch`

Big picture / architecture
- Single-page Angular application. Routes are defined in `src/app/app-routing.module.ts`:
  - `/restaurants` → `RestaurantListingComponent`
  - `/food-catalogue/:restaurantId` → `FoodCatalogueComponent`
  - `/order` → `OrderComponent`
- Core responsibilities:
  - `restaurant.service.ts`: talks to the Restaurant Service (`/restaurant` endpoints)
  - `food-catalogue.service.ts`: talks to Food Catalogue Service (`/foodCatalogue` endpoints)
  - `order.service.ts`: posts orders to Order Service (`/order` endpoints)
  - `cart.service.ts`: local client-side cart state using `BehaviorSubject`

API integration notes
- Services use hard-coded base URLs (examples in `src/app/services/*`):
  - Restaurant: `http://localhost:9091/restaurant`
  - Food Catalogue: `http://localhost:9093/foodCatalogue`
  - Order: `http://localhost:9094/order`
- There is a `proxy.conf.json` that maps `/api/*` paths to backend ports — prefer using the proxy when changing base URL during development.
  - Example proxy rules: `/api/restaurant` → `http://localhost:9091/restaurant` (see `proxy.conf.json`).

Component — service patterns (concrete examples)
- `FoodCatalogueComponent` calls `FoodCatalogueService.getFoodItemsByRestaurant(restaurantId)` and expects response shape `{ restaurant, foodItemsList }`.
- `RestaurantListingComponent` uses `RestaurantService.getAllRestaurants()` and navigates to `/food-catalogue/:id`.
- `CartService` stores `cartItems` and `selectedRestaurant` as `BehaviorSubject`s and exposes both observable streams and synchronous getters:
  - Use `cartService.cartItems$` to subscribe reactively, and `cartService.getCartItems()` for quick synchronous reads.

Models
- Models live in `src/app/models` (`restaurant.model.ts`, `food-item.model.ts`, `order.model.ts`).
- Use these models when constructing requests or mapping responses.

Testing and debugging
- Unit tests use Karma/Jasmine: `npm test`.
- Dev server source maps are enabled in development; run `npm start` (uses `serve` defaultConfiguration `development`).
- If facing CORS or API issues, check that backend services are running on ports `9091`, `9093`, and `9094` and that `proxy.conf.json` matches intended endpoints.

Conventions and small gotchas (repo-specific)
- Services use endpoint paths like `/fetchAllRestaurants`, `/fetchRestaurantAndFoodItemsById/{id}`, `/saveOrder`. Inspect service implementations before changing endpoints.
- Cart equality is determined by `itemName` in the current implementation (see `CartService`). Avoid refactoring equality without updating all callers.
- Components rely on alert dialogs and `confirm()` for lightweight UX flows (e.g., `OrderComponent.placeOrder`). Tests or automation should stub these calls.

When editing code
- Preserve existing public API and routing paths unless the change is coordinated with backend teams.
- Update `proxy.conf.json` or service base URLs if you change backend hosts/paths; prefer proxy for local dev.
- Keep UI behavior consistent with current flows: add-to-cart increments quantities via `quantities[item.itemName]` (see `FoodCatalogueComponent`).

Where to look for examples
- Routing and wiring: `src/app/app-routing.module.ts`, `src/app/app.module.ts`
- Service patterns & endpoints: `src/app/services/*.service.ts`
- Cart state management: `src/app/services/cart.service.ts`
- Component examples: `src/app/components/restaurant-listing/restaurant-listing.component.ts`, `src/app/components/food-catalogue/food-catalogue.component.ts`, `src/app/components/order/order.component.ts`

When you are uncertain
- Run the app locally (`npm install && npm start`) and exercise the flows: browse restaurants → view catalogue → add to cart → place order.
- If a backend endpoint or shape is unclear, inspect the service method (in `src/app/services`) and the component that consumes the response to infer the expected shape.

Edit notes
- Keep this file concise. If additional agent guidance is needed (e.g., CI, release process), ask a human maintainer and add a short section.

— End
