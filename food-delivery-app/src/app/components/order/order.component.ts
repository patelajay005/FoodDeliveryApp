import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FoodItem } from '../../models/food-item.model';
import { Restaurant } from '../../models/restaurant.model';
import { CartService } from '../../services/cart.service';
import { OrderService } from '../../services/order.service';
import { OrderRequest } from '../../models/order.model';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  cartItems: FoodItem[] = [];
  restaurant: Restaurant | null = null;
  userId: number = 1; // Default user ID for demo
  isPlacingOrder = false;
  orderPlaced = false;
  error = '';

  constructor(
    private cartService: CartService,
    private orderService: OrderService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cartService.cartItems$.subscribe(items => {
      this.cartItems = items;
    });

    this.cartService.selectedRestaurant$.subscribe(restaurant => {
      this.restaurant = restaurant;
    });
  }

  getTotalAmount(): number {
    return this.cartService.getTotalAmount();
  }

  updateQuantity(item: FoodItem, quantity: number): void {
    this.cartService.updateQuantity(item, quantity);
  }

  removeItem(item: FoodItem): void {
    if (confirm(`Remove ${item.itemName} from cart?`)) {
      this.cartService.removeFromCart(item);
    }
  }

  placeOrder(): void {
    if (!this.restaurant || this.cartItems.length === 0) {
      alert('Your cart is empty!');
      return;
    }

    if (confirm('Do you want to place this order?')) {
      this.isPlacingOrder = true;
      this.error = '';

      const orderRequest: OrderRequest = {
        foodCatalogueDTOS: this.cartItems,
        userId: this.userId,
        restaurant: this.restaurant
      };

      this.orderService.createOrder(orderRequest).subscribe({
        next: (response) => {
          this.isPlacingOrder = false;
          this.orderPlaced = true;
          
          setTimeout(() => {
            this.cartService.clearCart();
            this.router.navigate(['/restaurants']);
          }, 3000);
        },
        error: (err) => {
          this.isPlacingOrder = false;
          this.error = 'Failed to place order. Please try again.';
          console.error('Error placing order:', err);
        }
      });
    }
  }

  continueShopping(): void {
    if (this.restaurant) {
      this.router.navigate(['/food-catalogue', this.restaurant.id]);
    } else {
      this.router.navigate(['/restaurants']);
    }
  }

  clearCart(): void {
    if (confirm('Are you sure you want to clear the cart?')) {
      this.cartService.clearCart();
    }
  }
}

