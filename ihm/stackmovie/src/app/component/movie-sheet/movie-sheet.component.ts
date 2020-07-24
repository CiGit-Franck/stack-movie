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

  movie: Movie;

  constructor(private movieService: MovieService, private router: Router) { }

  ngOnInit(): void {
    // this.movieService.getMovie()
  }

}
