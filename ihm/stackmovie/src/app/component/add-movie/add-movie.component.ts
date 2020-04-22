import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import {Router} from '@angular/router';

import { Movie } from '../../model/movie';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {

  addAMovieForm: FormGroup;

  constructor(private formBuilder: FormBuilder) { 
    this.addAMovieForm = this.formBuilder.group({
      title: new FormControl(''),
      // released: new FormControl(''),
    });
  }

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  ngOnInit(): void {
  }

  onSubmit() {
    let addAMovie = this.addAMovieForm.value;
    if(addAMovie.title === ''){
      alert('Veuillez saisir unélément de recherche');
    } else {
      
    }
  }
}
