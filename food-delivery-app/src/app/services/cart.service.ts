import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { FoodItem } from '../models/food-item.model';
import { Restaurant } from '../models/restaurant.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItems = new BehaviorSubject<FoodItem[]>([]);
  private selectedRestaurant = new BehaviorSubject<Restaurant | null>(null);

  cartItems$ = this.cartItems.asObservable();
  selectedRestaurant$ = this.selectedRestaurant.asObservable();

  addToCart(item: FoodItem, restaurant: Restaurant) {
    const currentItems = this.cartItems.value;
    const existingItem = currentItems.find(i => i.itemName === item.itemName);
    
    if (existingItem) {
      existingItem.quantity += item.quantity;
      this.cartItems.next([...currentItems]);
    } else {
      this.cartItems.next([...currentItems, { ...item }]);
    }
    
    this.selectedRestaurant.next(restaurant);
  }

  removeFromCart(item: FoodItem) {
    const currentItems = this.cartItems.value.filter(i => i.itemName !== item.itemName);
    this.cartItems.next(currentItems);
    
    if (currentItems.length === 0) {
      this.selectedRestaurant.next(null);
    }
  }

  updateQuantity(item: FoodItem, quantity: number) {
    const currentItems = this.cartItems.value;
    const existingItem = currentItems.find(i => i.itemName === item.itemName);
    
    if (existingItem) {
      if (quantity <= 0) {
        this.removeFromCart(item);
      } else {
        existingItem.quantity = quantity;
        this.cartItems.next([...currentItems]);
      }
    }
  }

  clearCart() {
    this.cartItems.next([]);
    this.selectedRestaurant.next(null);
  }

  getCartItems(): FoodItem[] {
    return this.cartItems.value;
  }

  getSelectedRestaurant(): Restaurant | null {
    return this.selectedRestaurant.value;
  }

  getTotalAmount(): number {
    return this.cartItems.value.reduce((total, item) => 
      total + (Number(item.price) * item.quantity), 0
    );
  }

  getItemCount(): number {
    return this.cartItems.value.reduce((count, item) => count + item.quantity, 0);
  }
}

