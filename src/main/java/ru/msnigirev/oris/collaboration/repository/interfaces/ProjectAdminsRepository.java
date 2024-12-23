package ru.msnigirev.oris.collaboration.repository.interfaces;

import java.util.List;

public interface ProjectAdminsRepository {
    List<Integer> getAdmins(int projectId);

    List<Integer> getProjects(int userId);

    void addNewRelation(int projectId, int userId);
}
