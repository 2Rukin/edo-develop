create table if not exists address
(
    id           bigserial not null primary key,
    full_address varchar(60),
    street       varchar(30),
    house        varchar(10),
    index        int,
    housing      varchar(10),
    building     varchar(10),
    city         varchar(30),
    region       varchar(30),
    country      varchar(30),
    flat         varchar(30)
);
comment on table address
    is 'Адрес проживания';

comment on column address.full_address
    is 'Полный адрес';

comment on column address.street
    is 'Название улицы';

comment on column address.house
    is 'Номер дома';

comment on column address.index
    is 'Почтовый индекс';

comment on column address.housing
    is 'Корпус';

comment on column address.building
    is 'Строение';

comment on column address.city
    is 'Город';

comment on column address.region
    is 'Регион';

comment on column address.country
    is 'Страна';

comment on column address.flat
    is 'Квартира'