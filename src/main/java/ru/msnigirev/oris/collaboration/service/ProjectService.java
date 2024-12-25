package ru.msnigirev.oris.collaboration.service;

import ru.msnigirev.oris.collaboration.entity.Project;
import ru.msnigirev.oris.collaboration.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    Project getById(int id);

    ProjectDto getDtoById(int id);

    List<Project> getAllByAdmin(int adminId);

    void addNewProject(ProjectDto project);

    public List<Project> getAll(int offset, int size);

    List<Project> searchByName(String name);
}
