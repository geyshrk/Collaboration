package ru.msnigirev.oris.collaboration.repository.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.msnigirev.oris.collaboration.entity.Project;
import ru.msnigirev.oris.collaboration.repository.interfaces.ProjectRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Project> rowMapper;
    private final static String GET_BY_ID = "select * from projects where id = ?";
    private final static String CREATE = "INSERT INTO projects " +
            "(name, description, creator_id, subject_id, institute_id, teacher_id, year, avatar_url, folder) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String NEXT_ID = "SELECT MAX(id) FROM projects;";
    public ProjectRepositoryImpl(DataSource dataSource, RowMapper<Project> rowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
    }
    @Override
    public List<Project> getAllById(int id) {
        return List.of();
    }

    @Override
    public List<Project> getAll(int id, int offset, int size) {
        return List.of();
    }

    @Override
    public List<Project> getAllUsersProject(int projectId, int offset, int size) {
        return List.of();
    }

    @Override
    public List<Project> getAllUsersProject(int projectId) {
        return List.of();
    }

    @Override
    public int getMaxId() {
        try {
            return jdbcTemplate.queryForObject(NEXT_ID, Integer.class);
        }  catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public void create(Project data) {
        jdbcTemplate.update(CREATE,
                data.getName(),
                data.getDescription(),
                data.getCreatorId(),
                data.getSubjectId(),
                data.getInstituteId(),
                data.getTeacherId(),
                data.getYear(),
                data.getAvatar(),
                data.getFolder());
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
