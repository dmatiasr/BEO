 drop database if exists backenddb;

create database backenddb;


CREATE TABLE Users (
    id text primary key, 
    name text,
    addres text not null
);
