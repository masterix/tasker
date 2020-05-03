DROP TABLE IF EXISTS tasks;
CREATE TABLE tasks
(
    id          IDENTITY,
    title       VARCHAR(100),
    description VARCHAR,
    created_at  TIMESTAMP
);

CREATE TABLE attachments(
    filename VARCHAR(100) UNIQUE,
    task NUMERIC,
    comment VARCHAR,
    FOREIGN KEY (task) REFERENCES tasks (id)
);