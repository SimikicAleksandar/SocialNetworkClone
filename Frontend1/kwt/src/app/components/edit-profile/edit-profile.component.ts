import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit{
  editProfileForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.editProfileForm = this.formBuilder.group({
      displayName: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.editProfileForm.invalid) {
      return;
    }

    const userDTO: any = {
      displayName: this.editProfileForm.value.displayName,
      description: this.editProfileForm.value.description
    };

    this.userService.update(userDTO).subscribe(
      response => {
        // Uspješno ažuriranje
        console.log(response);
        this.router.navigateByUrl('/home')
      },
      error => {
        console.log(error);
      }
    );
  }
}
