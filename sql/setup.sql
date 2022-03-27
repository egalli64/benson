drop schema benson cascade;
create schema benson;

create table benson.user (
	user_id serial primary key,
	name text,
	password text,
	administrator boolean
);

insert into benson.user (name, password, administrator) values('root', 'password', true);
