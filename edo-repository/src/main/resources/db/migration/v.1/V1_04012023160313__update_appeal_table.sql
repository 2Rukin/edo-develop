create table if not exists appeal
(
    id            bigserial not null primary key,
    creation_date timestamp with time zone,
    archived_date timestamp with time zone,
    number        varchar(255),
    annotation    varchar(255),
    creator_id    bigint references employee (id)
);
comment on table appeal
    is 'Обращение';
comment on column appeal.creation_date
    is 'Дата создания обращения';
comment on column appeal.archived_date
    is 'Дата отработанного обращения';
comment on column appeal.number
    is 'Номер обращения';
comment on column appeal.annotation
    is 'Заголовок обращения';
comment on column appeal.creator_id
    is 'Создатель обращения';