create table if not exists tableList
(
    id serial primary key,
    name        varchar(255),
    data_base_id bigint not null references data_base (id),
    user_id     bigint not null references users (id)
);