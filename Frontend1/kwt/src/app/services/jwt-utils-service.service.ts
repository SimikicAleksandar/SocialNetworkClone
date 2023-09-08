import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtUtilsServiceService {

  constructor() { }

  decodeToken(token: string): any {
    try {
      const jwtData = token.split('.')[1];
      const decodedJwtJsonData = window.atob(jwtData);
      const decodedJwtData = JSON.parse(decodedJwtJsonData);
      return decodedJwtData;
    } catch (error) {
      console.error('Error decoding JWT token:', error);
      return null;
    }
  }
}
