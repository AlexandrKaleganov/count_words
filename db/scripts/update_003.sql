create table if not exists users
(
    id serial primary key,
    name     varchar(255),
    login    varchar(255),
    role_id  bigint references roles (id),
    pass varchar(255)
);