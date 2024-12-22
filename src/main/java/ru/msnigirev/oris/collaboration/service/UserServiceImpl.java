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
        return userRepository.getByUsername(username).orElse(null);
    }
    @Override
    public UserDto getUserDto(String username) {
        User user = userRepository.getByUsername(username).orElse(null);
        if (user == null) return null;
        return new UserDto(user.getUsername(),
                user.getPublicName(),
                user.getDescription(),
                user.getAvatarUrl());
    }
    @Override
    public String getUsernameByToken(String csrfToken) {
        return userRepository.getUsernameByToken(csrfToken);
    }


    @Override
    public void deleteCsrfToken(String csrfToken) {
        userRepository.deleteCsrfToken(csrfToken);
    }

    @Override
    public void addCsrfToken(String csrfToken, String username) {
        userRepository.addCsrfToken(csrfToken, username);
    }

    @Override
    public void addNewUser(String username, String publicName, String email, String phoneNumber, String password) {
        userRepository.addNewUser(username, publicName, email, phoneNumber, password);
    }

    @Override
    public boolean csrfTokenExists(String sessionId) {
        return userRepository.csrfTokenExists(sessionId);
    }

    @Override
    public void addAvatar(String url, String username) {
        userRepository.addAvatar(url, username);
    }

}
