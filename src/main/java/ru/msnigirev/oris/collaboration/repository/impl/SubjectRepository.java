package ru.msnigirev.oris.collaboration.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.msnigirev.oris.collaboration.repository.interfaces.ProjectTagRepository;

@RequiredArgsConstructor
public class SubjectRepository implements ProjectTagRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SELECT_SUBJECT_BY_ID = "SELECT name FROM subjects WHERE id = ?";
    private static final String INSERT_SUBJECT = "INSERT INTO subjects (name) VALUES (?)";

    @Override
    public String getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_SUBJECT_BY_ID, new Object[]{id}, String.class);
    }

    @Override
    public void add(String name) {
        jdbcTemplate.update(INSERT_SUBJECT, name);
    }
}

