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

create table brand
(
    id         integer,
    brand_name varchar
);

alter table brand
    owner to postgres;

INSERT INTO brand
(id, brand_name) VALUES
(1, 'Corona Extra'),
(2, 'Brahma'),
(3, 'Harbin'),
(4, 'Heineken'),
(5, 'Tsingtao'),
(6, 'Budweiser'),
(7, 'Bud'),
(8, 'Guinnes'),
(9, 'Newcastle'),
(10, 'Spaten'),
(11, 'St. Peters'),
(12, 'AF Brew');