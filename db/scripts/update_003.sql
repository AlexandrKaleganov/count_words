create table if not exists user
(
    id bigint auto_increment primary key,
    name     varchar(200),
    login    varchar(200),
    role_id  bigint references roles (id),
    password varchar(200)
);