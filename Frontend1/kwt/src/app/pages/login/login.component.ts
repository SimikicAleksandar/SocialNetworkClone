import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subject } from 'rxjs';
import { User } from 'src/app/models/user.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  PASSWORD_LENGTH: number = environment.PASSWORD_LENGTH;
  USERNAME_LENGTH: number = environment.USERNAME_LENGTH;
  errorMessage: string = "";
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private authService: AuthenticationService,
    private router: Router,
    private toastr: ToastrService) {
      this.loginForm = this.fb.group({
        username: ['', [Validators.required, Validators.minLength(this.USERNAME_LENGTH)]],
        password: ['', [Validators.required, Validators.minLength(this.PASSWORD_LENGTH)]]
      });
  }

  ngOnInit(): void {
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit(): void {
    const auth: any = {};
		auth.username = this.loginForm.value.username;
		auth.password = this.loginForm.value.password;

		this.authService.login(auth).subscribe(
			result => {
				this.toastr.success('Successful login!');
				localStorage.setItem('user', JSON.stringify(result));
				this.authService.isLoggedIn();
				this.router.navigateByUrl("/");
			},
			error => {
				this.toastr.error(error.error);
				console.error(error);
			}
		);

    
  }

  get username() {
    return this.loginForm.get('username')
  }

  get password() {
    return this.loginForm.get('password')
  }

  passwordHasErrorRequired() {
    return this.password?.hasError('required');
  }

  passwordHasErrorMinLength() {
    return this.password?.hasError('minlength');
  }

  usernameHasErrorRequired() {
    return this.username?.hasError('required');
  }

  usernameHasErrorMinLength() {
    return this.username?.hasError('minlength');
  }
}
