create table if not exists resolution_executor
(
    resolution_id bigint references resolution (id),
    employee_id bigint references employee (id)
);

comment on table resolution_executor is 'Таблица-связка между Resolution и Employee';
comment on column resolution_executor.resolution_id is 'Колонка для связки c resolution';
comment on column resolution_executor.employee_id is 'Колонка для связки с employee';