alter table edo.file_pool
    alter column upload_date type timestamp using upload_date::timestamp;

alter table edo.file_pool
    alter column archived_date type timestamp using archived_date::timestamp;

alter table edo.file_pool
    add constraint file_pool_creator_id_fkey
        foreign key (creator_id) references edo.employee (id);

alter table edo.file_pool
    alter column storage_file_id type uuid using storage_file_id::uuid;

alter table edo.file_pool
    drop constraint file_pool_size_check;

alter table edo.file_pool
    drop constraint file_pool_page_count_check;