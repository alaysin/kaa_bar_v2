create table drinks
(
    id       serial  not null
        constraint drinks_pk
            primary key,
    name     varchar not null,
    brand    varchar not null,
    price    integer not null,
    quantity integer not null,
    typ      varchar not null
);

alter table drinks
    owner to postgres;

create unique index drinks_id_uindex
    on drinks (id);

create sequence drinks_id_seq;

alter sequence drinks_id_seq owner to postgres;


create table users
(
    id        serial  not null
        constraint users_pk
            primary key,
    login     varchar not null,
    password  varchar not null,
    is_admin  boolean,
    name      varchar,
    last_name varchar not null,
    to_delete boolean
);

alter table users
    owner to postgres;

create unique index users_id_uindex
    on users (id);

create sequence users_id_seq;

alter sequence users_id_seq owner to postgres;

