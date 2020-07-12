import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { MovieService } from '../../service/movie.service';
import { Movie } from '../../model/movie';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {

  addAMovieForm: FormGroup;
  displayedColumns: any[] = ['add', 'title', 'released', 'idImdb', 'posterUrl'];
  dataSource = new MatTableDataSource<Movie>();
  hasData = false;

  constructor(private formBuilder: FormBuilder, private movieService: MovieService) {
    this.addAMovieForm = this.formBuilder.group({
      title: ['', Validators.required]
      // released: new FormControl(''),
    });
  }

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.addAMovieForm.invalid) {
      alert('Veuillez saisir unélément de recherche');
    } else {
      let addAMovie = this.addAMovieForm.value;
      this.movieService.getMoviesByKeyword(addAMovie.title).subscribe(movieSearch=>{
        this.dataSource.data = movieSearch;
        this.hasData = (movieSearch.length > 0);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      })
    }
  }
  
  addAmovie(movie: Movie) {
  }
}
