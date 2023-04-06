create table if not exists approval_block
(
    id                                bigserial primary key,
    approvalblock_type                varchar(45) not null,
    ordinal_number                    int
);

comment on table approval_block is 'Блок c участниками или подписантами согласования';
comment on column approval_block.id is 'Id';
comment on column approval_block.approvalblock_type is 'Тип блока листа согласования';
comment on column approval_block.ordinal_number is 'Номер по порядку согласования и порядку отображения на UI';