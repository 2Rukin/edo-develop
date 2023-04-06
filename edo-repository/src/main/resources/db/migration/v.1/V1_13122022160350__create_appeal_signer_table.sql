create table if not exists appeal_signer
(
    appeal_id bigint references edo.appeal (id),
    employee_id bigint references edo.employee (id)
);