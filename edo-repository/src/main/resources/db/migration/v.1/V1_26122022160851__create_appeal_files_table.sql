create table if not exists appeal_filepool
(
    appeal_id bigint references appeal (id),
    filepool_id bigint references file_pool (id)
);