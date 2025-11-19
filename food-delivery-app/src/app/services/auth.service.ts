import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly TOKEN_KEY = 'jwt_token';
  private readonly USER_KEY = 'user_info';
  private tokenSubject = new BehaviorSubject<string | null>(this.getToken());
  public token$ = this.tokenSubject.asObservable();

  constructor() {
    // Check if token exists in localStorage on service initialization
    const token = this.getToken();
    if (token) {
      this.tokenSubject.next(token);
    }
  }

  setToken(token: string, userInfo?: any): void {
    localStorage.setItem(this.TOKEN_KEY, token);
    if (userInfo) {
      localStorage.setItem(this.USER_KEY, JSON.stringify(userInfo));
    }
    this.tokenSubject.next(token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getUserInfo(): any {
    const userInfo = localStorage.getItem(this.USER_KEY);
    return userInfo ? JSON.parse(userInfo) : null;
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return token !== null && token.length > 0;
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_KEY);
    this.tokenSubject.next(null);
  }

  clearToken(): void {
    this.logout();
  }
}

