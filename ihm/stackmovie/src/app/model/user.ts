import {Movie} from './movie';

export class User {
    idUser: number;
    firstName: String;
    lastName: String;
    email: String;
    password: String;
    movies: Movie[];
}