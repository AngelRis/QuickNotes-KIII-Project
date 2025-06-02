import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { User } from '../models/user.model'
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
   imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  user: User = { username: '', password: '' };
  error = '';


  constructor(private authService: AuthService, private router: Router) {}

  register() {
  this.authService.register(this.user).subscribe({
    next: () => this.router.navigate(['/login']),
    error: err => this.error = err.error
  });
}
}
