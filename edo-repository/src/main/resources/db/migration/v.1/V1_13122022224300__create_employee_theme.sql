CREATE TABLE IF NOT EXISTS theme
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    archived_date TIMESTAMP WITH TIME ZONE,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    code  VARCHAR(20) NOT NULL,
    parent_theme bigint references theme (id)

);

COMMENT  ON TABLE  theme is 'таблица тем';
COMMENT  ON COLUMN theme.id is 'идентификатор';
COMMENT  ON COLUMN theme.name is 'название темы';
COMMENT  ON COLUMN theme.archived_date is 'дата архивации';
COMMENT  ON COLUMN theme.creation_date is 'дата создания';
COMMENT  ON COLUMN theme.code is 'идентификатор темы';
COMMENT  ON COLUMN theme.parent_theme is 'родительская тема';