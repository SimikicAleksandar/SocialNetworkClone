import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, catchError, map, tap } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { JwtUtilsServiceService } from './jwt-utils-service.service';
import { User } from '../models/user.model';
import { LoginResponse } from '../models/login.response.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
	private currentUser: User | null = null;
  	private headers = new HttpHeaders({'Content-Type': 'application/json'});
	

	constructor(
		private http: HttpClient,
		private router: Router,
		private jwtUtilsService: JwtUtilsServiceService
	) { 
		this.currentUser = this.getCurrentUser();
	}

	login(auth: any): Observable<any> {
		return this.http.post(environment.api + '/auth/login', {username: auth.username, password: auth.password}, {headers: this.headers}).pipe(
			map((response: any) => {
				const loginResponse = response as LoginResponse;
				this.currentUser = loginResponse.user;
				localStorage.setItem('user', JSON.stringify(loginResponse.user));
				return response;
			  }),
			  catchError(error => {
				console.error(error);
				throw error;
			  })
		);
	  }

	logout(): void {
		localStorage.clear();
		this.router.navigate(['/auth/login']);
	}

	isLoggedIn(): boolean {
		if (!localStorage.getItem('user')) {
				return false;
		}
		return true;
	}

	getToken(): string | null{
		const localStorageUser = localStorage.getItem("user")
    	if (localStorageUser) {
      		const currentUser = JSON.parse(localStorageUser);

			return currentUser.accessToken;
    	}
		
    	return null;
	}

	getAuthenticatedHeaders(): HttpHeaders {
		const token = this.getToken();
		if (token) {
			return this.headers.set('Authorization', `Bearer ${token}`);
		}
		return this.headers;
	}

	getCurrentUser(): User | null {
        const token = this.getToken();
        if (token) {
          const decodedToken = this.jwtUtilsService.decodeToken(token);
          if (decodedToken && decodedToken.sub) {
            return decodedToken;
          }
        }
        return null;
    }

	getCurrentUsername() {
		const token = this.getToken();
		if (token) {
		  const decodedToken = this.jwtUtilsService.decodeToken(token);
		  if (decodedToken && decodedToken.sub) {
			return decodedToken.sub;
		  }
		}
		return null;
	}
}
