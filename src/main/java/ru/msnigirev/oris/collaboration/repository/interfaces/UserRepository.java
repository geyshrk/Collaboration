package ru.msnigirev.oris.collaboration.repository.interfaces;

import ru.msnigirev.oris.collaboration.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    List<User> getAllByName(String name);

    String getUsernameByToken(String csrfToken);

    void deleteCsrfToken(String csrfToken);

    void addCsrfToken(String csrfToken, String username);

    boolean csrfTokenExists(String csrfToken);

    void addNewUser(String username, String publicName, String email, String phoneNumber, String password);
}
