DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS attachments;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS tags_tasks;

CREATE TABLE projects
(
    id   IDENTITY,
    name VARCHAR(100)
);

CREATE TABLE tasks
(
    id          IDENTITY,
    project     NUMERIC,
    title       VARCHAR(100),
    description VARCHAR,
    created_at  TIMESTAMP,
    FOREIGN KEY (project) REFERENCES projects (id) ON DELETE CASCADE
);

CREATE TABLE attachments
(
    id       IDENTITY,
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
    tag  NUMERIC NOT NULL,
    task NUMERIC NOT NULL,
    FOREIGN KEY (tag) REFERENCES tags (id),
    FOREIGN KEY (task) REFERENCES tasks (id)
);