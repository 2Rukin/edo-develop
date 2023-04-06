create table if not exists author
(
    id             bigserial   not null primary key,
    first_name     varchar(20) not null,
    last_name      varchar(20) not null,
    middle_name    varchar(20) not null,
    address        varchar(20) not null,
    snils          varchar(20) not null,
    mobile_phone   int         not null,
    email          varchar(20) not null,
    employment     varchar(20) not null,
    fio_dative     varchar(60) not null,
    fio_genitive   varchar(60) not null,
    fio_nominative varchar(60) not null

);

comment on table author is 'Таблица "Автор обращения';
comment on column author.id is 'ID';
comment on column author.first_name is 'Имя';
comment on column author.last_name is 'Фамилия';
comment on column author.middle_name is 'Отчество';
comment on column author.address is 'Адрес';
comment on column author.snils is 'СНИЛС';
comment on column author.mobile_phone is 'Номер телефона';
comment on column author.email is 'Адрес электронной почты';
comment on column author.employment is 'трудоустройство автора (Безработный, Работник, Учащийся)';
comment on column author.fio_dative is 'ФИО автора в дательном падеже';
comment on column author.fio_genitive is 'ФИО автора в родительном падеже';
comment on column author.fio_nominative is 'ФИО автора в именительном падеже';