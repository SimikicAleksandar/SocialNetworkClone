import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent implements OnInit{
  loggedIn: boolean = false;
  username: string = "";
  displayName: string = "";

  constructor(
    public authService: AuthenticationService,
    private userService: UserService,
    private router: Router
  ){}

  ngOnInit(): void {
    this.updateNavbar();

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.updateNavbar();
      }
    });
    this.userService.getUsername() ? this.username = this.userService.getUsername() : ""
    
  }

  updateNavbar() {
    this.loggedIn = this.authService.isLoggedIn();
  }

  onLogout(){
    this.authService.logout();
  }

}
