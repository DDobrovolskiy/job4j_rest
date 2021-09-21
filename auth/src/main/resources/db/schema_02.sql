create table IF NOT EXISTS employees (
   id serial primary key not null,
   name varchar(2000),
   surname varchar(2000),
   inn int,
   employment timestamp without time zone not null default now()
);

create table IF NOT EXISTS persons (
   id serial primary key not null,
   login varchar(2000) UNIQUE,
   password varchar(2000),
   employee_id integer NOT NULL,
   CONSTRAINT persone_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES employees (id)
);
