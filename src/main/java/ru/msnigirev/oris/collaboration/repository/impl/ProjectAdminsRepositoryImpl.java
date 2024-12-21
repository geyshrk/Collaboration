package ru.msnigirev.oris.collaboration.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.msnigirev.oris.collaboration.repository.interfaces.ProjectAdminsRepository;

import java.util.List;

@RequiredArgsConstructor
public class ProjectAdminsRepositoryImpl implements ProjectAdminsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final static String GET_ALL_ADMINS_BY_ID =  "select * from project_admins where project_id = ?";
    @Override
    public List<Integer> getAdmins(int projectId) {
        return jdbcTemplate.queryForList(GET_ALL_ADMINS_BY_ID, new Object[]{projectId}, Integer.class);
    }
}
