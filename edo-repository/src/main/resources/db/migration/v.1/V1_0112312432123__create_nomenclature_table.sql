create table if not exists nomenclature (
    id bigserial primary key not null,
    creation_date timestamp with time zone,
    archived_date timestamp with time zone,
    template varchar(300),
    current_value int,
    "index" int
);

comment on table nomenclature is 'Таблица номенкталура';
comment on column nomenclature.id is 'идентификатор';
comment on column nomenclature.creation_date is 'дата создания';
comment on column nomenclature.archived_date is 'дата архивации';
comment on column nomenclature.template is 'шаблон записи';
comment on column nomenclature.current_value is 'текущее значение';
comment on column nomenclature."index" is 'индекс записи';
