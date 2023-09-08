import { Component, OnInit } from '@angular/core';
import { User } from './models/user.model';
import { UserService } from './services/user.service';
import { AuthenticationService } from './services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'kwt';
  message = '';

  constructor(private router: Router, public authService: AuthenticationService){}
  ngOnInit(): void {
    this.authService.getCurrentUser();
  }

  logout(): void{
    localStorage.removeItem('user');
    this.router.navigateByUrl('/auth/login').then()
  }
}
