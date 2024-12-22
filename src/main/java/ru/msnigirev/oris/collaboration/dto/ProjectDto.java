package ru.msnigirev.oris.collaboration.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
public class ProjectDto {
    private int id;
    private String name;
    private String description;
    private int creatorId;
    private String teacher;
    private String institute;
    private String subject;
    private int year;
    // url + file
    private Map<String, File> projectStructure;
    private Set<User> admins;
    /*
    CREATE TABLE projects (
                          project_id SERIAL PRIMARY KEY,
                          project_name VARCHAR(100) NOT NULL,
                          description TEXT,
                          creator_id INT NOT NULL,
                          FOREIGN KEY (creator_id) REFERENCES users(user_id) ON DELETE CASCADE
    );
     */
}
