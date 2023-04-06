CREATE TABLE question
(
    id            BIGSERIAL   NOT NULL PRIMARY KEY,
    creation_date timestamptz NOT NULL,
    archived_date timestamptz,
    summary       VARCHAR     NOT NULL
);

COMMENT ON TABLE question is 'информация о вопросе';
COMMENT ON COLUMN question.id is 'id вопроса';
COMMENT ON COLUMN question.creation_date is 'дата создания';
COMMENT ON COLUMN question.archived_date is 'дата архивации';
COMMENT ON COLUMN question.summary is 'краткое содержание вопроса';
