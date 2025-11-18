import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order, OrderRequest } from '../models/order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'http://localhost:9094/order';

  constructor(private http: HttpClient) { }

  createOrder(order: OrderRequest): Observable<Order> {
    return this.http.post<Order>(`${this.apiUrl}/saveOrder`, order);
  }
}

