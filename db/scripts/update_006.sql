create table if not exists tableList
(
    id bigint auto_increment primary key,
    name        varchar(255),
    dataBase_id bigint references data_base (id),
    user_id     bigint references user (id)
);