import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FoodItem } from '../models/food-item.model';

@Injectable({
  providedIn: 'root'
})
export class FoodCatalogueService {
  private apiUrl = 'http://localhost:9093/foodCatalogue';

  constructor(private http: HttpClient) { }

  getFoodItemsByRestaurant(restaurantId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/fetchRestaurantAndFoodItemsById/${restaurantId}`);
  }

  addFoodItem(foodItem: FoodItem): Observable<FoodItem> {
    return this.http.post<FoodItem>(`${this.apiUrl}/addFoodItem`, foodItem);
  }
}

