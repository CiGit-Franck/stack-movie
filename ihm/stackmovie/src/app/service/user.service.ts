import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
      })
  };

  private pathRootApi = 'http://localhost:8090/users';

  public currentUser: Observable<User>;

  constructor(private httpClient: HttpClient) { 
  }

  public getUserWithLogin(login: string, pwd: string): Observable<User> {
    const urlApi = this.pathRootApi + '/login';
    const credential = {
      mail: login,
      password: pwd
    };
    this.currentUser = this.httpClient.post<User>(urlApi, credential, this.httpOptions);
    return this.currentUser;
  }

  public getCurrentUser(): Observable<User> {
    return this.currentUser;
  }

  public addUser(newUser: User): Observable<User> {
    const urlApi = this.pathRootApi;
    return this.httpClient.post<User>(urlApi, newUser, this.httpOptions);
  } 
}
