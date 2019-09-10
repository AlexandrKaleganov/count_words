create table if not exists app_list
(
    id bigint auto_increment primary key,
    tableList_id bigint references tableList (id),
    app_id       bigint references app (id)
);