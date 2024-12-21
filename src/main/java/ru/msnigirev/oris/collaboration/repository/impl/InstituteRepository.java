package ru.msnigirev.oris.collaboration.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.msnigirev.oris.collaboration.repository.interfaces.ProjectTagRepository;

@RequiredArgsConstructor
public class InstituteRepository implements ProjectTagRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SELECT_INSTITUTE_BY_ID = "SELECT name FROM institutes WHERE id = ?";
    private static final String INSERT_INSTITUTE = "INSERT INTO institutes (name) VALUES (?)";

    @Override
    public String getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_INSTITUTE_BY_ID, new Object[]{id}, String.class);
    }

    @Override
    public void add(String name) {
        jdbcTemplate.update(INSERT_INSTITUTE, name);
    }
}
