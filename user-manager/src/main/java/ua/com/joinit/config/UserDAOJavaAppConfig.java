package ua.com.joinit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.com.joinit.DAO.UserDAO;
import ua.com.joinit.DAO.impl.UserDAOImpl;

/**
 * Created by krupet on 3/5/15.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.com.joinit")
public class UserDAOJavaAppConfig {

    @Bean
    public UserDAO userDAO() {
        return new UserDAOImpl();
    }
}
