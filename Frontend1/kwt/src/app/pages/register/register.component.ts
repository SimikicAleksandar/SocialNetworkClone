import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  email?: string;
  firstName?: string;
  lastName?: string;
  username?: string;
  password?: string;
  showSuccess: boolean = false;

  constructor(private userService: UserService, private router: Router){}

  ngOnInit(): void {
  }


  register(){
    const user = {
      email: this.email,
      firstName: this.firstName,
      lastName: this.lastName,
      username: this.username,
      password: this.password
    };

    this.userService.register(user).subscribe(
      () => {
        this.router.navigateByUrl("/auth/login");
      },
      (error) => {
        console.log(error);
      }
    );
  }


}
