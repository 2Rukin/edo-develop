create table if not exists approval_signatoryapprovalblock
(
    approval_id      bigint references approval (id),
    approvalblock_id bigint references approval_block (id)
)