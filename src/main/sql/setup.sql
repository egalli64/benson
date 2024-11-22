-- H2 Database setup
-- java -cp h2.jar org.h2.tools.RunScript -url jdbc:h2:./benson -script setup.sql

drop schema if exists benson cascade;
drop user if exists benson;

create user benson password 'password';
create schema benson;
grant all on schema benson to benson;

use benson;

drop table if exists account;

create table account (
	account_id identity primary key,
	name text unique not null,
	password text not null,
	administrator boolean not null
);

insert into account (name, password, administrator) values
	('root', 'il46qnBMTuYj+q8NE9OI5Q==', true),
	('guest', 'RhrUE1U/mdh4TYGk/E35fA==', false);
