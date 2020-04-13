import { Genre } from './genre';
import { Actor } from './actor';
import { Director } from './director';

export class Movie {
    idMovie: number;
    title: String;
    date: String;
    story: String;
    imdbRating: number;
    imdbVote: number;
    genres: Genre[];
    actors: Actor[];
    directors: Director[];
}