package ru.msnigirev.oris.collaboration.service;

import lombok.AllArgsConstructor;
import ru.msnigirev.oris.collaboration.dto.UserDto;
import ru.msnigirev.oris.collaboration.entity.User;
import ru.msnigirev.oris.collaboration.repository.interfaces.UserRepository;

import java.util.List;


@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public List<UserDto> getAllUsers(int offset, int size) {
        List<User> all = userRepository.getAll(offset, size);
        return all.stream()
                .map(user -> new UserDto(user.getUsername(),
                        user.getPublicName(),
                        user.getDescription(),
                        user.getAvatarUrl()))
                .toList();
    }

    @Override
    public User getUser(String username) {
        return userRepository.getById(username).orElse(null);
    }

    @Override
    public void deleteSessionId(String sessionId) {
        userRepository.deleteSessionId(sessionId);
    }

    @Override
    public void addSessionId(String sessionId, String username) {
        userRepository.addSessionId(sessionId, username);
    }

    @Override
    public void addNewUser(String username, String email, String phoneNumber, String password) {
        userRepository.addNewUser(username, email, phoneNumber, password);
    }

    @Override
    public boolean sessionIdExists(String sessionId) {
        return userRepository.sessionIdExists(sessionId);
    }

}
