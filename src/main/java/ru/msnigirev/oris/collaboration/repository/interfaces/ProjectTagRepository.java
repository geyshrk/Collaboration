package ru.msnigirev.oris.collaboration.repository.interfaces;

public interface ProjectTagRepository {
    String getById(int id);
    void add(String name);
}

