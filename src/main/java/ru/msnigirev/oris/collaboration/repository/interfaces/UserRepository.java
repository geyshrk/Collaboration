package ru.msnigirev.oris.collaboration.repository.interfaces;

import ru.msnigirev.oris.collaboration.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    List<User> getAllByName(String name);

    void deleteSessionId(String sessionId);

    void addSessionId(String sessionId, String username);

    boolean sessionIdExists(String sessionId);

    void addNewUser(String username, String email, String phoneNumber, String password);
}
