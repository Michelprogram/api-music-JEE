drop table if exists album_genre;
drop table if exists album;
drop table if exists artist;
drop table if exists genre;

create table album(id int not null primary key, artist_id int, date_album varchar(255), name varchar(255), number_track int, price float);

create table album_genre (album_id integer not null, genre_id integer not null, primary key (album_id, genre_id));
create table artist (id integer not null, name varchar(255), pays varchar(255), primary key (id));
create table genre (id integer not null, name varchar(255), primary key (id));

alter table album add constraint FKmwc4fyyxb6tfi0qba26gcf8s1 foreign key (artist_id) references artist;
alter table album_genre add constraint FKld60pu9t8ff70bc6nrnnv91lx foreign key (genre_id) references genre;
alter table album_genre add constraint FKgobybiodeygwcmlhr7sxv4y01 foreign key (album_id) references album;