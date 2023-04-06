create table if not exists appeal_question
(
    appeal_id bigint references appeal (id),
    question_id bigint references question (id)
);