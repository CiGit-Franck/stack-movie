import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Movie } from '../model/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private pathRootApi = 'http://localhost:8090/movies';

  public listMovie: Observable<Movie[]>;
  public listSearch: Movie[];
  public currentMovie: Movie;

  constructor(private httpClient: HttpClient) { }

  getMovie(idImdb: string): Observable<Movie> {
    const urlApi = this.pathRootApi + '/imdb/' + idImdb;
    return this.httpClient.get<Movie>(urlApi);
  }

  getMoviesByKeyword(keyword: string): Observable<Movie[]> {
    const urlApi = this.pathRootApi + '/search/' + keyword;
    return this.httpClient.get<Movie[]>(urlApi);
  }
}
