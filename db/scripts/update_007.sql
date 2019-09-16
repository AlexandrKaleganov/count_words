create table if not exists app__table_list
(
    id serial primary key,
    tableList_id bigint references tableList (id),
    app_id       bigint references apps (id)
);