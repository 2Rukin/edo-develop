create table if not exists approvalblock_signatory
(
    approvalblock_id bigint references approval_block (id),
    member_id        bigint references member (id)
)