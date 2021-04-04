import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

import { AppComponent } from './app.component';

import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';

import { TopBarComponent } from './component/top-bar/top-bar.component';
import { FootBarComponent } from './component/foot-bar/foot-bar.component';
import { HomePageComponent } from './component/home-page/home-page.component';
import { LoginPageComponent } from './component/login-page/login-page.component';
import { AddUserComponent } from './component/add-user/add-user.component';
import { AddMovieComponent } from './component/add-movie/add-movie.component';

import { UserService } from './service/user.service';
import { MovieService } from './service/movie.service';
import { MovieSheetComponent } from './component/movie-sheet/movie-sheet.component';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    HomePageComponent,
    FootBarComponent,
    LoginPageComponent,
    AddUserComponent,
    AddMovieComponent,
    MovieSheetComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule.forRoot([
    { path: '', component: HomePageComponent },
    { path: 'login', component: LoginPageComponent },
    { path: 'adduser', component: AddUserComponent },
    { path: 'addamovie', component: AddMovieComponent },
    { path: 'movies/imdb/:imdbId', component: MovieSheetComponent },
], { relativeLinkResolution: 'legacy' }),
    FlexLayoutModule,
    MatButtonModule,
    MatToolbarModule,
    MatCardModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    FormsModule, 
    ReactiveFormsModule
  ],
  providers: [UserService, MovieService],
  bootstrap: [AppComponent]
})

export class AppModule { }
