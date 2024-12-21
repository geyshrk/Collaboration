package ru.msnigirev.oris.collaboration.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.msnigirev.oris.collaboration.entity.Project;
import ru.msnigirev.oris.collaboration.repository.interfaces.ProjectRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Project> rowMapper;
    private final static String GET_BY_ID = "select * from projects where project_id = ?";
    @Override
    public List<Project> getAllById(int id) {
        return List.of();
    }

    @Override
    public List<Project> getAll(int id, int offset, int size) {
        return List.of();
    }

    @Override
    public Optional<Project> getById(Integer id) {

        List<Project> projects = jdbcTemplate.query(GET_BY_ID, rowMapper, id);
        return optionalSingleResult(projects);
    }

    private Optional<Project> optionalSingleResult(List<Project> projects) {
        if (projects.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1);
        } else {
            return projects.stream().findAny();
        }
    }
    @Override
    public List<Project> getAll() {
        return List.of();
    }

    @Override
    public List<Project> getAll(int offset, int size) {
        return List.of();
    }

    @Override
    public boolean update(Project type) {
        return false;
    }

    @Override
    public boolean delete(Project type) {
        return false;
    }
}
