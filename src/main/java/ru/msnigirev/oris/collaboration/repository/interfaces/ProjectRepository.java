package ru.msnigirev.oris.collaboration.repository.interfaces;

import ru.msnigirev.oris.collaboration.entity.Project;
import ru.msnigirev.oris.collaboration.entity.User;

import java.util.List;
import java.util.Set;

public interface ProjectRepository extends CrudRepository<Project, Integer>{
    List<Project> getAllById(int id);

    List<Project> getAll(int projectId, int offset, int size);

    List<Project> getAllUsersProject(int projectId, int offset, int size);

    List<Project> getAllUsersProject(int projectId);

    int getMaxId();
}
