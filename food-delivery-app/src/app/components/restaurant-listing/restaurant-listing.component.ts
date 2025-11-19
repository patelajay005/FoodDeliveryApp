import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Restaurant } from '../../models/restaurant.model';
import { RestaurantService } from '../../services/restaurant.service';

@Component({
  selector: 'app-restaurant-listing',
  templateUrl: './restaurant-listing.component.html',
  styleUrls: ['./restaurant-listing.component.css']
})
export class RestaurantListingComponent implements OnInit {
  restaurants: Restaurant[] = [];
  loading = true;
  error = '';

  constructor(
    private restaurantService: RestaurantService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadRestaurants();
  }

  loadRestaurants(): void {
    this.loading = true;
    this.error = '';
    this.restaurantService.getAllRestaurants().subscribe({
      next: (data) => {
        this.restaurants = data;
        this.loading = false;
        this.error = '';
      },
      error: (err) => {
        this.loading = false;
        console.error('Error loading restaurants:', err);
        
        if (err.status === 401 || err.status === 403) {
          this.error = 'Authentication required. Please login again.';
        } else if (err.status === 0) {
          this.error = 'Unable to connect to server. Please check if the backend is running.';
        } else {
          this.error = `Failed to load restaurants. Error: ${err.status} ${err.statusText || 'Unknown error'}`;
        }
      }
    });
  }

  viewMenu(restaurantId: number): void {
    this.router.navigate(['/food-catalogue', restaurantId]);
  }
}

