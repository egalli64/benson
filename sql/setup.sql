-- Before running heroku psql, ensure Postgres, Heroku, Git are available!
-- set path=C:\Program Files\PostgreSQL\14\bin;C:\Program Files\heroku\bin;C:\Program Files\Git\bin
--
-- To run this script from psql: \i setup.sql
--

drop schema benson cascade;
create schema benson;

create table benson.user (
	user_id serial primary key,
	name text unique not null,
	password text not null,
	administrator boolean not null
);

insert into benson.user (name, password, administrator) values('root', 'password', true);
insert into benson.user (name, password, administrator) values('guest', 'guest', false);
