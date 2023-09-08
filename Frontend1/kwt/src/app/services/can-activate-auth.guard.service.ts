import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class CanActivateAuthGuardService {

  constructor(private authenticationService: AuthenticationService, private router: Router) { }
  canActivate(): boolean {
    if(this.authenticationService.isLoggedIn()){
      this.router.navigateByUrl("/home");
      return false;
    }
    return true;
  }
};
