CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       public_name VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       phone VARCHAR(15),
                       avatar_url VARCHAR(255),
                       description TEXT
);
CREATE TABLE projects (
                          project_id SERIAL PRIMARY KEY,
                          project_name VARCHAR(100) NOT NULL,
                          description TEXT,
                          creator_id INT,
                          FOREIGN KEY (creator_id) REFERENCES users(user_id) ON DELETE CASCADE
);
CREATE TABLE project_admins (
                                project_id INT,
                                user_id INT,
                                PRIMARY KEY (project_id, user_id),
                                FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE,
                                FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
CREATE TABLE project_materials (
                                   material_id SERIAL PRIMARY KEY,
                                   project_id INT,
                                   material_type VARCHAR(50),  -- Тип материала (например, document, image, video)
                                   material_url VARCHAR(255),   -- URL или путь к материалу
                                   description TEXT,
                                   uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE
);
CREATE TABLE project_sections (
                                  section_id SERIAL PRIMARY KEY,
                                  project_id INT,
                                  section_name VARCHAR(100),
                                  description TEXT,
                                  FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE
);

CREATE TABLE project_tasks (
                               task_id SERIAL PRIMARY KEY,
                               section_id INT,
                               task_name VARCHAR(100),
                               description TEXT,
                               status VARCHAR(20) DEFAULT 'pending',  -- Статус задачи (например, pending, in_progress, completed)
                               due_date DATE,
                               FOREIGN KEY (section_id) REFERENCES project_sections(section_id) ON DELETE CASCADE
);
