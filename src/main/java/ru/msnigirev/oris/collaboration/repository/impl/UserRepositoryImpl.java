package ru.msnigirev.oris.collaboration.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.msnigirev.oris.collaboration.entity.User;
import ru.msnigirev.oris.collaboration.repository.interfaces.UserRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper;

    private final static String GET_BY_USERNAME = "select * from users where username = ?";
    private final static String GET_USERNAME_BY_TOKEN = "select username from users where csrf_token = ?";
    private final static String GET_ALL = "select * from users";
    private final static String GET_ALL_PAGEABLE = "select * from users offset ? limit ?";
    private final static String DELETE_CSRF_TOKEN = "UPDATE users SET csrf_token = NULL WHERE csrf_token = ?";
    private final static String ADD_CSRF_TOKEN = "UPDATE users SET csrf_token = ? WHERE username = ?";
    private final static String GET_BY_CSRF_TOKEN = "select * from users where csrf_token = ?";
    private final static String ADD_NEW_USER = "INSERT INTO users (username, public_name, email, phone, password) " +
            "VALUES (?, ?, ?, ?, ?);";

    public UserRepositoryImpl(DataSource dataSource, RowMapper<User> rowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
    }

    @Override
    public Optional<User> getById(String username) {
        List<User> users = jdbcTemplate.query(GET_BY_USERNAME, rowMapper, username);
        return optionalSingleResult(users);
    }

    private Optional<User> optionalSingleResult(List<User> users) {
        if (users.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1);
        } else {
            return users.stream().findAny();
        }
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(GET_ALL, rowMapper);
    }

    @Override
    public List<User> getAll(int offset, int size) {
        return jdbcTemplate.query(GET_ALL_PAGEABLE, rowMapper, offset, size);
    }

    @Override
    public boolean update(User type) {
        return false;
    }

    @Override
    public boolean delete(User type) {
        return false;
    }


    @Override
    public List<User> getAllByName(String name) {
        return null;
    }

    @Override
    public String getUsernameByToken(String csrfToken) {
        User user = jdbcTemplate.query(GET_USERNAME_BY_TOKEN, rowMapper, csrfToken).stream().findFirst().orElse(null);
        if (user == null) return null;
        return user.getUsername();
    }

    @Override
    public void deleteCsrfToken(String csrfToken) {
        jdbcTemplate.update(DELETE_CSRF_TOKEN, csrfToken);
    }

    @Override
    public void addCsrfToken(String csrfToken, String username) {
        jdbcTemplate.update(ADD_CSRF_TOKEN, csrfToken, username);
    }

    @Override
    public boolean csrfTokenExists(String csrfToken) {
        List<User> users = jdbcTemplate.query(GET_BY_CSRF_TOKEN, rowMapper, csrfToken);
        return optionalSingleResult(users).isPresent();
    }

    @Override
    public void addNewUser(String username, String publicName, String email, String phoneNumber, String password) {
        jdbcTemplate.update(ADD_NEW_USER, username, publicName, email, phoneNumber, password);
    }

}
