alter table if exists approval_block
    add approval_id                       bigint references approval (id),
    add member_who_redirected_approval_id bigint references member (id);

comment on column approval_block.approval_id is 'Лист согласования, за которым закреплён блок';
comment on column approval_block.member_who_redirected_approval_id is 'Участник перенаправивший лист согласования';