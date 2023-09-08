import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';
import { Comment } from '../models/post.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  public save(postId:number, comment: Comment): Observable<Comment>{
    const headers = this.authService.getAuthenticatedHeaders();
    return this.http.post<Comment>(`${environment.api}/comments/${postId}`, comment, {headers, responseType: 'json'});
  }

  public update(commentId: number, newText: string): Observable<Comment>{
    const body = {
      text: newText
    };
    const headers = this.authService.getAuthenticatedHeaders();
    return this.http.put<Comment>(`${environment.api}/comments/${commentId}`, body, {headers, responseType: 'json'});
  }

  public getCommentsFromPost(postId: number): Observable<Comment[]> {
    const headers = this.authService.getAuthenticatedHeaders();
    return this.http.get<Comment[]>(`${environment.api}/comments/${postId}`, {headers, responseType: 'json'});
  }

  delete(commentId: number): Observable<void>{
    const headers = this.authService.getAuthenticatedHeaders();
    return this.http.delete<void>(`${environment.api}/comments/${commentId}`, {headers, responseType: 'json'});
  }
}
