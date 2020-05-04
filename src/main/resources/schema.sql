DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS attachments;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS tags_tasks;

CREATE TABLE tasks
(
    id          IDENTITY,
    title       VARCHAR(100),
    description VARCHAR,
    created_at  TIMESTAMP
);

CREATE TABLE attachments
(
    filename VARCHAR(100) UNIQUE,
    task     NUMERIC,
    comment  VARCHAR,
    FOREIGN KEY (task) REFERENCES tasks (id)
);

CREATE TABLE tags
(
    id   IDENTITY,
    name VARCHAR(100)
);

CREATE TABLE tags_tasks
(
    tag NUMERIC NOT NULL,
    task NUMERIC NOT NULL,
    FOREIGN KEY (tag) REFERENCES tags (id),
    FOREIGN KEY (task) REFERENCES tasks (id)
);