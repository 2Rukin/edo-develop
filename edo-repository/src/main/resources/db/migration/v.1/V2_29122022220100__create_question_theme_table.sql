alter table if exists question
    add theme_id bigint references theme (id);

comment on column question.theme_id
    is 'тема id';