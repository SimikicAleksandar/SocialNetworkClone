import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  changePasswordForm!: FormGroup;
  errorMessage!: string;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.changePasswordForm = this.formBuilder.group({
      currentPassword: ['', Validators.required],
      newPassword: ['', Validators.required],
      newPassword1: ['', Validators.required]
    });
  }

  onSubmit() {
    const currentPassword = this.changePasswordForm.value.currentPassword;
    const newPassword = this.changePasswordForm.value.newPassword;
    const newPassword1 = this.changePasswordForm.value.newPassword1;

    // Provjera da li se nove lozinke podudaraju
    if (newPassword !== newPassword1) {
      this.errorMessage = "New passwords do not match.";
      return;
    }

    // Poziv metode za promjenu lozinke u UserService
    this.userService.changePassword(currentPassword, newPassword, newPassword1).subscribe(
      () => {
        // Ovdje možete dodati dodatnu logiku nakon uspješne promjene lozinke
        localStorage.clear();
        this.router.navigateByUrl("/auth/login")
      },
      error => {
        this.errorMessage = error;
        console.log('Error occurred: ', error);
      }
    );
  }

}
