import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(public auth: AuthenticationService, public router: Router) { }

  canActive(): boolean {
    if(this.auth.isLoggedIn()){
      this.router.navigateByUrl('/home').then()
      return false;
    }
    return true;
  }
}
