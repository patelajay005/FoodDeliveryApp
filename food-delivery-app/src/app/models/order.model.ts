import { FoodItem } from './food-item.model';
import { Restaurant } from './restaurant.model';

export interface Order {
  id?: string;
  foodCatalogueDTOS: FoodItem[];
  userId: number;
  restaurant: Restaurant;
}

export interface OrderRequest {
  foodCatalogueDTOS: FoodItem[];
  userId: number;
  restaurant: Restaurant;
}

