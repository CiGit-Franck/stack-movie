import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Movie } from '../model/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private pathRootApi = 'http://localhost:8080/movies';

  public currentUser: Observable<Movie>;

  constructor(private httpClient: HttpClient) { }

  getMoviesByKeyword(keyword: string): Observable<Movie[]> {
    const urlApi = this.pathRootApi + 'movies/search/' + keyword;
    return this.httpClient.get<Movie[]>(urlApi);
  }
}