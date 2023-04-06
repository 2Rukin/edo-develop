create table if not exists appeal_addressee
(
    appeal_id bigint references edo.appeal (id),
    employee_id bigint references edo.employee (id)
);