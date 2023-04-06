create table if not exists approval
(
    id                          bigserial primary key,
    appeal_id                   bigint references appeal (id),
    initiator_id                bigint references member (id),
    initiator_comment           varchar(255),
    creation_date               timestamp with time zone not null,
    date_sent_for_approval      timestamp with time zone,
    signing_date                timestamp with time zone,
    return_date_for_revision    timestamp with time zone,
    return_processing_date      timestamp with time zone,
    archived_date               timestamp with time zone,
    current_member_id           bigint references member (id)
);

comment on table approval is 'Лист согласования';
comment on column approval.id is 'Id';
comment on column approval.appeal_id is 'Обращение, за которым закреплён данный лист согласования';
comment on column approval.initiator_id is 'Инициатор(участник с типом инициатор)';
comment on column approval.initiator_comment is 'Комментарий инициатора';
comment on column approval.creation_date is 'Дата создания листа согласования';
comment on column approval.date_sent_for_approval is 'Дата направления на согласование';
comment on column approval.signing_date is 'Дата подписания';
comment on column approval.return_date_for_revision is 'Дата возврата на доработку';
comment on column approval.return_processing_date is 'Дата обработки возврата';
comment on column approval.archived_date is 'Дата архивации листа согласования';
comment on column approval.current_member_id is 'Текущий участник листа согласования';