-- Вставка пользователей
INSERT INTO users (username, public_name, password, email, phone, avatar_url, csrf_token, description) VALUES
           ('misha', 'misha', '$2a$10$IbWs8mLgKTuia45EzCfc8eKaaKlbkNZj6s9rffajB/3q.OJAZUxDO', 'geyshrk@mail.ru', '1234567890', 'http://example.com/avatars/john.jpg', gen_random_uuid(), 'A passionate developer.'),
           ('jane_smith', 'Jane Smith', 'password456', 'jane@example.com', '0987654321', 'http://example.com/avatars/jane.jpg', gen_random_uuid(), 'An enthusiastic teacher.'),
           ('alice_jones', 'Alice Jones', 'password789', 'alice@example.com', '1122334455', 'http://example.com/avatars/alice.jpg', gen_random_uuid(), 'A project manager.');

-- Вставка учителей
INSERT INTO teachers (name) VALUES
                                ('Mr. Anderson'),
                                ('Ms. Johnson'),
                                ('Dr. Brown');

-- Вставка предметов
INSERT INTO subjects (name) VALUES
                                ('Mathematics'),
                                ('Physics'),
                                ('Computer Science'),
                                ('Biology');

-- Вставка институтов
INSERT INTO institutes (name) VALUES
                 ('Institute of Technology'),
                 ('School of Arts'),
                 ('College of Science');

-- Вставка проектов
INSERT INTO projects (name, description, creator_id, subject_id, institute_id, teacher_id, year) VALUES
   ('Project Alpha', 'A project about advanced algorithms.', 1, 3, 1, 1, 2023),
   ('Project Beta', 'A study on renewable energy sources.', 2, 2, 2, 2, 2023),
   ('Project Gamma', 'Research on machine learning applications.', 1, 3, 3, 3, 2023);

-- Вставка администраторов проектов
INSERT INTO project_admins (project_id, user_id) VALUES
                                                     (1, 1),
                                                     (1, 2),
                                                     (2, 2),
                                                     (3, 1),
                                                     (3, 3);
