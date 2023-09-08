import { Injectable } from '@angular/core';
import { Group } from '../models/group.model';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';
import { environment } from 'src/environments/environment.development';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  constructor(private http: HttpClient, private authenticationService: AuthenticationService){}

  public save(group: Group): Observable<Group> {
    const headers = this.authenticationService.getAuthenticatedHeaders();
    return this.http.post<Group>(`${environment.api}/group`, group, { headers });
  }

  public update(groupId: number, name: string, description: string): Observable<Group> {
    const headers = this.authenticationService.getAuthenticatedHeaders();
    const body = {
      name,
      description
    }
    return this.http.put<Group>(`${environment.api}/group/${groupId}`, body, { headers });
  }

  public delete(id: number): Observable<void> {
    const headers = this.authenticationService.getAuthenticatedHeaders();
    return this.http.delete<void>(`${environment.api}/group/${id}`, {headers});
  }

  public getAll(): Observable<Group[]>{
    return this.http.get<Group[]>(`${environment.api}/group/all`);
  }

  public getOne(id: number): Observable<Group> {
    return this.http.get<Group>(`${environment.api}/group/${id}`);
  }
}
