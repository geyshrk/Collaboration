package ru.msnigirev.oris.collaboration.service;

import ru.msnigirev.oris.collaboration.entity.Project;
import ru.msnigirev.oris.collaboration.entity.ProjectDto;

public interface ProjectService {
    Project getById(int id);

    ProjectDto getDtoById(int id);
}
