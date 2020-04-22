import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

import { Movie } from '../../model/movie';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  movies = new MatTableDataSource<Movie>();
  moviesColumns = ['title', 'directors', 'actors', 'genres', 'story'];

  constructor(private userService: UserService) { }

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(currentUser => {
      this.movies.data = currentUser.movies;
      this.movies.paginator = this.paginator;
      this.movies.sort = this.sort;
    });
  }

}

function compare(a: string, b: string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
