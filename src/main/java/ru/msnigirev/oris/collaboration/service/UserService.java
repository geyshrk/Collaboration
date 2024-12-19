package ru.msnigirev.oris.collaboration.service;

import ru.msnigirev.oris.collaboration.dto.UserDto;
import ru.msnigirev.oris.collaboration.entity.User;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();

    List<UserDto> getAllUsers(int offset, int size);

    User getUser(String username);

    void deleteSessionId(String sessionId);

    void addSessionId(String sessionId, String username);

    void addNewUser(String username, String email, String phoneNumber, String password);

    boolean sessionIdExists(String sessionId);
}
