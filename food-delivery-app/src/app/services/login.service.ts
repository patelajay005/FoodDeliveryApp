import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { AuthService } from './auth.service';

export interface LoginRequest {
  userName: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  userId: number;
  userName: string;
  expiresIn: number;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:9092/auth';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, loginRequest, {
      headers: { 'Content-Type': 'application/json' }
    }).pipe(
      tap(response => {
        // Store token and user info
        if (response && response.token) {
          this.authService.setToken(response.token, {
            userId: response.userId,
            userName: response.userName
          });
        }
      })
    );
  }

  logout(): void {
    this.authService.logout();
  }
}

