INSERT INTO `projects` (`name`)
VALUES ('Projekt nr 1');

INSERT INTO `tasks`(`title`, `project`, `description`, `created_at`)
VALUES ('Dokończyć zadanie', 1, 'Opis do zadania', NOW()),
 ('Obejrzeć moduł 6', 1, 'Opis do zadania', NOW()),
 ('Stworzyć własny projekt', 1, 'Opis do zadania z GH', NOW());

INSERT INTO `tags`(`name`)
VALUES ('URGENT'),
('Home'),
('Job');