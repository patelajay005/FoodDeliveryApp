import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantListingComponent } from './components/restaurant-listing/restaurant-listing.component';
import { FoodCatalogueComponent } from './components/food-catalogue/food-catalogue.component';
import { OrderComponent } from './components/order/order.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'restaurants', component: RestaurantListingComponent, canActivate: [AuthGuard] },
  { path: 'food-catalogue/:restaurantId', component: FoodCatalogueComponent, canActivate: [AuthGuard] },
  { path: 'order', component: OrderComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

