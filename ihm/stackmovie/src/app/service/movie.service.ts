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

  constructor(private httpClient: HttpClient) { }

  getMovie(imdbId: string): Observable<any> {
    const urlApi = this.pathRootApi + '/imdb/' + imdbId;
    return this.httpClient.get<Movie>(urlApi);
  }

  getMoviesByKeyword(keyword: string): Observable<Movie[]> {
    const urlApi = this.pathRootApi + '/search/' + keyword;
    return this.httpClient.get<Movie[]>(urlApi);
  }
}
