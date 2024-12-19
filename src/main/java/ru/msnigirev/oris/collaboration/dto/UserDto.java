package ru.msnigirev.oris.collaboration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserDto {
    private String username;
    private String publicName;
    private String description;
    private String avatarUrl;
    /*
    CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       phone VARCHAR(15),
                       avatar_url VARCHAR(255),
                       description TEXT
    );
     */
}