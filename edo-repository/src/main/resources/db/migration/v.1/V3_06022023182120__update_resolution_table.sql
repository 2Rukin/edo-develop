ALTER TABLE if exists resolution
    ADD COLUMN is_draft boolean default 'FALSE',
    ADD COLUMN task varchar (255);
ALTER TABLE resolution
    add question BIGINT REFERENCES question(id);


comment
ON COLUMN resolution.is_draft is 'Черновик';
comment
ON COLUMN resolution.task is 'Задача';
comment
ON COLUMN resolution.question is 'Вопросы';