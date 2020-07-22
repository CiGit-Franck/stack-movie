import { Genre } from './genre';
import { Actor } from './actor';
import { Director } from './director';

export class Movie {
    idMovie: number;
    imdbId: string;
    title: String;
    date: String;
    story: String;
    imdbRating: number;
    imdbVote: number;
    posterUrl: string;
    genres: Genre[];
    actors: Actor[];
    directors: Director[];
}