ALTER TABLE employee
    ALTER COLUMN first_name TYPE varchar(100),
    ALTER COLUMN last_name TYPE varchar(100),
    ALTER COLUMN middle_name TYPE varchar(100),
    ALTER COLUMN fio_dative TYPE varchar(100),
    ALTER COLUMN fio_nominative TYPE varchar(100),
    ALTER COLUMN fio_genitive TYPE varchar(100),
    ALTER COLUMN phone TYPE varchar(100),
    ALTER COLUMN work_phone TYPE varchar(100),
    ALTER COLUMN username TYPE varchar(100),
    DROP COLUMN address;

alter table if exists employee
    add department BIGINT REFERENCES department(id),
    add address BIGINT REFERENCES address(id);


comment on column employee.first_name
    is 'Имя';

comment on column employee.last_name
    is 'Фамилия';

comment on column employee.middle_name
    is 'Отчество';

comment on column employee.fio_dative
    is 'ФИО в дательном падеже';

comment on column employee.fio_nominative
    is 'ФИО в именительном падеже';

comment on column employee.fio_genitive
    is 'ФИО в родительном падеже';

comment on column employee.phone
    is 'Номер телефона сотовый';

comment on column employee.work_phone
    is 'Рабочий номер телефона';

comment on column employee.username
    is 'Имя пользователя';

comment on column employee.department
    is 'Департамент';

comment on column employee.address
    is 'Адрес';