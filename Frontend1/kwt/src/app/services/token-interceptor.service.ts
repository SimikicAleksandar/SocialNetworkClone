import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    const item = localStorage.getItem('user');
    const decodedItem = item ? JSON.parse(item) : null;

    if(item) {
      const cloned = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${decodedItem.token}`)
      });

      return next.handle(cloned);
    } else {
      return next.handle(req);
    }
  }

  
}
