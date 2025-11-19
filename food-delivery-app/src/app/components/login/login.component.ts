import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  userName: string = '';
  password: string = '';
  loading = false;
  error = '';
  success = false;

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  onSubmit(): void {
    if (!this.userName || !this.password) {
      this.error = 'Please enter both username and password';
      return;
    }

    this.loading = true;
    this.error = '';
    this.success = false;

    this.loginService.login({
      userName: this.userName,
      password: this.password
    }).subscribe({
      next: (response) => {
        this.loading = false;
        this.success = true;
        console.log('Login successful! Token:', response.token);
        // Redirect to restaurants page after successful login
        setTimeout(() => {
          this.router.navigate(['/restaurants']);
        }, 1000);
      },
      error: (err) => {
        this.loading = false;
        if (err.status === 401) {
          this.error = 'Invalid username or password';
        } else {
          this.error = 'Login failed. Please try again.';
        }
        console.error('Login error:', err);
      }
    });
  }
}

