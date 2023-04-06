create table if not exists member
(
    id                bigserial primary key,
    member_type       varchar(45)              not null,
    creation_date     timestamp with time zone not null,
    execution_date    timestamp with time zone,
    date_of_receiving timestamp with time zone,
    end_date          timestamp with time zone,
    ordinal_number    int                      not null,
    employee_id       bigint                   not null references employee (id)
);

comment on table member is 'Участник';
comment on column member.id is 'Id';
comment on column member.member_type is 'Тип участника согласования';
comment on column member.creation_date is 'Дата создания участника';
comment on column member.execution_date is 'Дата, до которой должно быть исполнено';
comment on column member.date_of_receiving is 'Дата получения';
comment on column member.end_date is 'Дата завершения действия';
comment on column member.ordinal_number is 'Номер по порядку согласования и порядку отображения на UI';
comment on column member.employee_id is 'Работник, который является данным участником';