import {Movie} from './movie';

export class User {
    
    idUser: number;
    firstName: String;
    lastName: String;
    mail: String;
    password: String;
    movies: Movie[];

    public constructor(init?: Partial<User>) {
        Object.assign(this, init);
    }
}