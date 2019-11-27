create table users(
user_id numeric primary key,
email varchar(50) unique not null,
first_name varchar(50) not null,
last_name varchar(50) not null,
phone_number varchar(20) not null,
role numeric not null,
ride_status numeric not null,
account_status boolean,
location_id numeric not null
);

create table car(
car_id numeric primary key,
user_id numeric references users(user_id),
seats numeric not null
);

create sequence user_id_seq;
create sequence car_id_seq;

--drop sequence user_id_seq;
--drop sequence car_id_seq;

--drop table car;
--drop table users;