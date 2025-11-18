import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantListingComponent } from './components/restaurant-listing/restaurant-listing.component';
import { FoodCatalogueComponent } from './components/food-catalogue/food-catalogue.component';
import { OrderComponent } from './components/order/order.component';

const routes: Routes = [
  { path: '', redirectTo: '/restaurants', pathMatch: 'full' },
  { path: 'restaurants', component: RestaurantListingComponent },
  { path: 'food-catalogue/:restaurantId', component: FoodCatalogueComponent },
  { path: 'order', component: OrderComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

