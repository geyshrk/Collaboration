package ru.msnigirev.oris.collaboration.repository.interfaces;

import java.util.List;

public interface ProjectAdminsRepository {
    List<Integer> getAdmins(int projectId);
}
