import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user: User = new User();
  editProfileEnabled: boolean = false;
  editPasswordEnabled: boolean = false;
  loggedIn!: boolean;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private authService: AuthenticationService
  ){}


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const username = params['username'];
      this.userService.getUser(username).subscribe((user: User) => {
        this.user = user;
      })
    })
    this.loggedIn = this.authService.isLoggedIn();
  }

  onEditProfile(){
    this.editProfileEnabled = !this.editProfileEnabled;
  }

  onEditPassword(){
    this.editPasswordEnabled = !this.editPasswordEnabled;
  }

  onUpdateProfile(user: User){
    this.user = user;
    this.editProfileEnabled = false;
    this.editPasswordEnabled = false;
  }

  
}
