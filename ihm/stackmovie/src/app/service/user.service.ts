import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  private pathRootApi = 'http://localhost:8080/';

  public currentUser: Observable<User>;

  constructor(private httpClient: HttpClient) { 
  }

  public getUserWithLogin(mail: string, pwd: string) {
    const urlApi = this.pathRootApi + 'users/'
    const credential = {
      login: mail,
      password: pwd
    };
    this.currentUser = this.httpClient.post<User>(urlApi, credential, this.httpOptions);
    return this.getCurrentUser();
  }


  public getCurrentUser(){
    return this.currentUser;
  }

  public addUser(newUser: User) {
    const urlApi = this.pathRootApi + 'users/'
    return this.httpClient.post<User>(urlApi, newUser, this.httpOptions);
  }
}
