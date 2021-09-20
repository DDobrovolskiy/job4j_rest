create table IF NOT EXISTS persons (
   id serial primary key not null,
   login varchar(2000) UNIQUE,
   password varchar(2000)
);
