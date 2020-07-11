/**
 * Author:  Franck
 * Created: 9 juil. 2020
 */

drop table if exists app_user cascade;
drop table if exists movie cascade;
drop table if exists actor cascade;
drop table if exists director cascade;
drop table if exists genre cascade;

drop sequence if exists user_seq_id cascade;
drop sequence if exists movie_seq_id cascade;
drop sequence if exists actor_seq_id cascade;
drop sequence if exists director_seq_id cascade;
drop sequence if exists genre_seq_id cascade;
