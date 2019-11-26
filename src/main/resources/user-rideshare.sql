create table car(
car_id numeric primary key,
seats int not null
);

create table users(
user_id numeric primary key,
email varchar(50) unique not null,
first_name varchar(50) not null,
last_name varchar(50) not null,
phone_number varchar(20) not null,
role varchar(25) not null,
ride_status varchar(25) not null,
account_status boolean,
location_id numeric not null,
car_id numeric references car(car_id)
);

create sequence user_id_seq;
create sequence car_id_seq;

--drop sequence user_id_seq;
--drop sequence car_id_seq;

--drop table users;
--drop table car;