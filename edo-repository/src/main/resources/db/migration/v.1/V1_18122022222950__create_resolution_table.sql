create table if not exists resolution
(
    id               bigserial primary key,
    creation_date    timestamp with time zone not null,
    archived_date    timestamp with time zone,
    last_action_date timestamp with time zone not null,
    resolution_type  varchar(20)              not null,
    creator_id       bigint references employee (id),
    signer_id        bigint references employee (id),
    curator_id       bigint references employee (id),
    serial_number    varchar(50)              not null
);

comment on table resolution is 'Резолюция';
comment on column resolution.id is 'Id';
comment on column resolution.creation_date is 'Дата создания резолюции';
comment on column resolution.archived_date is 'Дата архивации резолюции';
comment on column resolution.last_action_date is 'Дата последнего действия над резолюцией';
comment on column resolution.resolution_type is 'Вид - резолюция, направление или запрос';
comment on column resolution.creator_id is 'Создатель резолюции';
comment on column resolution.signer_id is 'Принимающий резолюцию';
comment on column resolution.curator_id is 'Куратор исполнения';
comment on column resolution.serial_number is 'Номер резолюции';

