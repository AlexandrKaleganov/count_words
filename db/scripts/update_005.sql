create table if not exists apps
(
    id serial primary key,
    id_app      bigint,
    name        varchar(255),
    exten       varchar(255),
    port        varchar(255),
    project_name varchar(255),
    data_base_id bigint not null references data_base (id)
);