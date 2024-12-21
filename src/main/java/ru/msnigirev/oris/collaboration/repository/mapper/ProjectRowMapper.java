package ru.msnigirev.oris.collaboration.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.msnigirev.oris.collaboration.entity.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Project.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .creatorId(resultSet.getInt("creator_id"))
                .year(resultSet.getInt("year"))
                .build();
    }

}
