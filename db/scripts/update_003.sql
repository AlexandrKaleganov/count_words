create table if not exists user
(
    id       serial primary key,
    name     varchar(200),
    login    varchar(200),
    role_id  integer references roles (id),
    password varchar(200)
);