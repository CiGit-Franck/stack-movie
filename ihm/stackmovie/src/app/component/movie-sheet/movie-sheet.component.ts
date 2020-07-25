import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Movie } from 'src/app/model/movie';
import { MovieService } from '../../service/movie.service';

@Component({
  selector: 'app-movie-sheet',
  templateUrl: './movie-sheet.component.html',
  styleUrls: ['./movie-sheet.component.css']
})
export class MovieSheetComponent implements OnInit {

  public movie: Movie;

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.movie = this.movieService.currentMovie;
  }

}
