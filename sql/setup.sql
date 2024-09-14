--
-- to access the remote instance from local
-- psql "postgres://<USER>:<PASSWORD>@dpg-crin63bv2p9s738l0ae0-a.frankfurt-postgres.render.com/benson_ldjp"
--

drop schema if exists benson cascade;
create schema benson;

create table benson.user (
	user_id serial primary key,
	name text unique not null,
	password text not null,
	administrator boolean not null
);

insert into benson.user (name, password, administrator) values('root', 'il46qnBMTuYj+q8NE9OI5Q==', true);
insert into benson.user (name, password, administrator) values('guest', 'RhrUE1U/mdh4TYGk/E35fA==', false);
