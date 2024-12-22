CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       public_name VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       phone VARCHAR(15) NOT NULL UNIQUE,
                       avatar_url VARCHAR(255),
                       csrf_token uuid,
                       description TEXT
);
CREATE TABLE teachers (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE subjects (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE institutes (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);
CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          creator_id INT,
                          subject_id INT,
                          institute_id INT,
                          teacher_id INT,
                          year INT,
                          FOREIGN KEY (creator_id) REFERENCES users(user_id) ON DELETE CASCADE,
                          FOREIGN KEY (subject_id) REFERENCES subjects(id),
                          FOREIGN KEY (teacher_id) REFERENCES teachers(id),
                          FOREIGN KEY (institute_id) REFERENCES institutes(id)
);
CREATE TABLE project_admins (
                                project_id INT,
                                user_id INT,
                                PRIMARY KEY (project_id, user_id),
                                FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
                                FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
CREATE TABLE project_materials (
                                   material_id SERIAL PRIMARY KEY,
                                   project_id INT,
                                   material_type VARCHAR(50),  -- Тип материала (например, document, image, video)
                                   material_url VARCHAR(255),   -- URL или путь к материалу
                                   description TEXT,
                                   uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);


