create table if not exists data_base
(
    id bigint auto_increment primary key,
    name     varchar(255),
    ip_bd    varchar(255) unique,
    password varchar(255),
    user_id  bigint references user (id)
);