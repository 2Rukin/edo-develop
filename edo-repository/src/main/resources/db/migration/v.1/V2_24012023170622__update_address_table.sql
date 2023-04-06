alter table if exists address
    alter column longitude DROP NOT NULL,
    alter column latitude DROP NOT NULL;
