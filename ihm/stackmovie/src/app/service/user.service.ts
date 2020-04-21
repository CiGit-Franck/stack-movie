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

  private pathRootApi = 'http://localhost:8080/';

  public currentUser: Observable<User>;

  constructor(private httpClient: HttpClient) { 
  }

  public getUserWithLogin(login: string, pwd: string): Observable<User> {
    const urlApi = this.pathRootApi + 'users/login';
    const credential = {
      mail: login,
      password: pwd
    };
    return this.httpClient.post<User>(urlApi, credential, this.httpOptions);
  }

  public getCurrentUser(): Observable<User> {
    return this.currentUser;
  }

  public addUser(newUser: User): Observable<User> {
    const urlApi = this.pathRootApi + 'users';
    return this.httpClient.post<User>(urlApi, newUser, this.httpOptions);
  } 
}
