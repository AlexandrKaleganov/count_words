create table if not exists user
(
    id bigint auto_increment primary key,
    name     varchar(255),
    login    varchar(255),
    role_id  bigint references roles (id),
    password varchar(255)
);