package ru.msnigirev.oris.collaboration.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;
import ru.msnigirev.oris.collaboration.config.DataSourceConfiguration;
import ru.msnigirev.oris.collaboration.repository.impl.UserRepositoryImpl;
import ru.msnigirev.oris.collaboration.repository.interfaces.UserRepository;
import ru.msnigirev.oris.collaboration.repository.mapper.UserRowMapper;
import ru.msnigirev.oris.collaboration.service.UserService;
import ru.msnigirev.oris.collaboration.service.UserServiceImpl;

import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataSourceConfiguration configuration =
                new DataSourceConfiguration(properties);

        Flyway flyway = Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(configuration.hikariDataSource())
//                .dataSource(
//                        properties.getProperty("database.url"),
//                        properties.getProperty("database.username"),
//                        properties.getProperty("database.password")
//                )
                .load();
        flyway.migrate();
        UserRepository userRepository =
                new UserRepositoryImpl(configuration.hikariDataSource(), new UserRowMapper());

        UserService userService = new UserServiceImpl(userRepository);

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userService", userService);

    }
}
