import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodItem } from '../../models/food-item.model';
import { Restaurant } from '../../models/restaurant.model';
import { FoodCatalogueService } from '../../services/food-catalogue.service';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-food-catalogue',
  templateUrl: './food-catalogue.component.html',
  styleUrls: ['./food-catalogue.component.css']
})
export class FoodCatalogueComponent implements OnInit {
  foodItems: FoodItem[] = [];
  restaurant: Restaurant | null = null;
  loading = true;
  error = '';
  restaurantId: number = 0;
  quantities: { [key: string]: number } = {};

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private foodCatalogueService: FoodCatalogueService,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.restaurantId = +params['restaurantId'];
      this.loadFoodCatalogue();
    });
  }

  loadFoodCatalogue(): void {
    this.loading = true;
    this.foodCatalogueService.getFoodItemsByRestaurant(this.restaurantId).subscribe({
      next: (data) => {
        console.log('Received data from API:', data);
        this.restaurant = data.restaurant;
        // Backend returns 'foodCatalogueDTOS', not 'foodItemsList'
        this.foodItems = data.foodCatalogueDTOS || data.foodItemsList || [];
        
        console.log('Food items after mapping:', this.foodItems);
        
        // Initialize quantities for each item individually using ID or itemName as key
        this.quantities = {};
        this.foodItems.forEach(item => {
          const key = item.id ? `id_${item.id}` : item.itemName;
          if (key) {
            this.quantities[key] = 1;
          }
        });
        
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load food items. Please try again later.';
        this.loading = false;
        console.error('Error loading food catalogue:', err);
      }
    });
  }

  getItemKey(item: FoodItem): string {
    return item.id ? `id_${item.id}` : item.itemName;
  }

  incrementQuantity(item: FoodItem): void {
    const key = this.getItemKey(item);
    if (!this.quantities[key]) {
      this.quantities[key] = 1;
    }
    this.quantities[key] = (this.quantities[key] || 0) + 1;
    // Force change detection by creating a new object reference
    this.quantities = { ...this.quantities };
  }

  decrementQuantity(item: FoodItem): void {
    const key = this.getItemKey(item);
    if (!this.quantities[key]) {
      this.quantities[key] = 1;
    }
    if (this.quantities[key] > 1) {
      this.quantities[key] = this.quantities[key] - 1;
      // Force change detection by creating a new object reference
      this.quantities = { ...this.quantities };
    }
  }

  addToCart(item: FoodItem): void {
    if (this.restaurant) {
      const key = this.getItemKey(item);
      const itemToAdd = {
        ...item,
        quantity: this.quantities[key] || 1
      };
      
      this.cartService.addToCart(itemToAdd, this.restaurant);
      alert(`${item.itemName} added to cart!`);
      
      // Reset quantity after adding to cart
      this.quantities[key] = 1;
      // Force change detection
      this.quantities = { ...this.quantities };
    }
  }

  goToCart(): void {
    this.router.navigate(['/order']);
  }

  goBack(): void {
    this.router.navigate(['/restaurants']);
  }

  getCartItemCount(): number {
    return this.cartService.getItemCount();
  }
}

