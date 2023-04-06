create table if not exists appeal_authors
(
    appeal_id bigint references appeal (id),
    author_id bigint references author (id)
);