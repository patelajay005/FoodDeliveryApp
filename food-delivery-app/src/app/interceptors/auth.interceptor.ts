import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    // Skip adding token for login and registration endpoints
    if (request.url.includes('/auth/login') || request.url.includes('/user/adduser')) {
      return next.handle(request);
    }

    const token = this.authService.getToken();

    // Add Authorization header if token exists
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      // Debug: Log that token is being added (remove in production)
      console.log('JWT Token added to request:', request.url);
    } else {
      // Debug: Log if no token is available
      console.warn('No JWT token available for request:', request.url);
    }

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        // Handle 401 Unauthorized errors
        if (error.status === 401) {
          // Clear token and redirect to login if token is invalid
          this.authService.logout();
          // Redirect to login page
          this.router.navigate(['/login']);
        }
        return throwError(() => error);
      })
    );
  }
}

