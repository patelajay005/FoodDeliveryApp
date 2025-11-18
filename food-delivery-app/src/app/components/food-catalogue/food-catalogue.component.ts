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
        this.restaurant = data.restaurant;
        this.foodItems = data.foodItemsList || [];
        
        // Initialize quantities
        this.foodItems.forEach(item => {
          this.quantities[item.itemName] = 1;
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

  incrementQuantity(itemName: string): void {
    this.quantities[itemName]++;
  }

  decrementQuantity(itemName: string): void {
    if (this.quantities[itemName] > 1) {
      this.quantities[itemName]--;
    }
  }

  addToCart(item: FoodItem): void {
    if (this.restaurant) {
      const itemToAdd = {
        ...item,
        quantity: this.quantities[item.itemName]
      };
      
      this.cartService.addToCart(itemToAdd, this.restaurant);
      alert(`${item.itemName} added to cart!`);
      
      // Reset quantity after adding to cart
      this.quantities[item.itemName] = 1;
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

