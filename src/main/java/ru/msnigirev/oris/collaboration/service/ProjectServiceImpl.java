package ru.msnigirev.oris.collaboration.service;

import ru.msnigirev.oris.collaboration.entity.Project;
import ru.msnigirev.oris.collaboration.repository.interfaces.ProjectRepository;

public class ProjectServiceImpl implements ProjectService {
    //Возможно здесь определенные репозитории или список репозиториев для тегов
    ProjectRepository projectRepository;
    @Override
    public Project getById(int id) {
        Project project = projectRepository.getById(id).orElse(null);
        if (project == null) return null;
        return null;
    }
}
