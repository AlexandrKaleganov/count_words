create table if not exists data_base
(
    id serial primary key,
    name     varchar(255),
    ip_bd    varchar(255) unique,
    password varchar(255),
    user_id  bigint references users (id)
);