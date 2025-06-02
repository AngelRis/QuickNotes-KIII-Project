import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { User } from '../models/user.model';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
   currentUser: User | null = null;


   constructor(private authService: AuthService,private router: Router){}

   ngOnInit() {
  this.authService.getUser().subscribe(user => {
  
    this.currentUser = user;
  });
   }
   logout(){
    this.authService.logout().subscribe({
    next: () => this.router.navigate(['/login']),
    error: err => console.error('Logout failed', err)
  });
   }
   
}

