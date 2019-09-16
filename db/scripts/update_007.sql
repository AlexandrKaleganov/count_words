create table if not exists app_table_list
(
    id serial primary key,
    tableList_id bigint references tableList (id),
    app_id       bigint references apps (id)
);