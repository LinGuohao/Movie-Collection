drop table if exists movie;

create table movie (
  id varchar(255),
  name varchar(255)
);

drop table if exists info;

create table info (
      id varchar(255),
      name varchar(255),
      IMDb  DOUBLE,
      Tomatoes int
);
