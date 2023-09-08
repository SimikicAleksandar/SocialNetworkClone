import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Post } from '../models/post.model';
import { environment } from 'src/environments/environment.development';
import { User } from '../models/user.model';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }

  public save(post: Post): Observable<Post> {
    const headers = this.authenticationService.getAuthenticatedHeaders();
    return this.http.post<Post>(`${environment.api}/post`, post, { headers });
  }

  public update(postId: number, postContent: string): Observable<Post> {
    const headers = this.authenticationService.getAuthenticatedHeaders();
    return this.http.put<Post>(`${environment.api}/post/${postId}`, postContent, { headers });
  }

  public delete(id: number): Observable<void> {
    const headers = this.authenticationService.getAuthenticatedHeaders();
    return this.http.delete<void>(`${environment.api}/post/${id}`, {headers});
  }

  public getAll(): Observable<Post[]>{
    return this.http.get<Post[]>(`${environment.api}/post/all`);
  }

  public getOne(id: number): Observable<Post> {
    return this.http.get<Post>(`${environment.api}/post/${id}`);
  }
}
