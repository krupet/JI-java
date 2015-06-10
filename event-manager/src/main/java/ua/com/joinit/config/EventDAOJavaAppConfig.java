package ua.com.joinit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.dao.UserDAO;
import ua.com.joinit.dao.impl.EventDAOImpl;
import ua.com.joinit.dao.impl.UserDAOImpl;

/**
 * Created by krupet on 3/5/15.
 */
@Configuration
@ComponentScan("ua.com.joinit")
public class EventDAOJavaAppConfig {

    @Bean
    public EventDAO eventDAO() {
        return new EventDAOImpl();
    }
}
