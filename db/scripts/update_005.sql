create table if not exists app
(
    id bigint auto_increment primary key,
    id_app      bigint,
    name        varchar(255),
    exten       varchar(255),
    port        varchar(255),
    project_name varchar(255),
    dataBase_id bigint references data_base (id)
);