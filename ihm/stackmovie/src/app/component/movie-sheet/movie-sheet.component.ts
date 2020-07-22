import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/model/movie';

@Component({
  selector: 'app-movie-sheet',
  templateUrl: './movie-sheet.component.html',
  styleUrls: ['./movie-sheet.component.css']
})
export class MovieSheetComponent implements OnInit {

  currentMovie: Movie;

  constructor() { }

  ngOnInit(): void {
  }

}
