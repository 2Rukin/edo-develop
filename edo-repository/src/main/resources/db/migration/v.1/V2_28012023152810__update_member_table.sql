alter table if exists member
    add approvalblock_id bigint references approval_block (id);

comment on column member.approvalblock_id is 'Блок листа согласования в котором находится участник';