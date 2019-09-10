create table if not exists data_base
(
    id bigint auto_increment primary key,
    name     varchar(250),
    ip_db    varchar(250),
    password varchar(250),
    user_id  bigint references user (id)
);