package ua.com.joinit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.com.joinit.service.UserService;
import ua.com.joinit.service.UserTestService;
import ua.com.joinit.service.impl.UserServiceImpl;
import ua.com.joinit.service.impl.UserTestServiceImpl;
import ua.com.joinit.service.mock.UserMockDAO;
import ua.com.joinit.service.mock.UserMockDAOImpl;

/**
 * Created by krupet on 28.01.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.com.joinit")
@ImportResource({"classpath:app-config.xml"})
public class UsersJavaAppConfig {
    @Bean
    public UserTestService userTestService() {
        return new UserTestServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserMockDAO userMockDAO() {
        return new UserMockDAOImpl();
    }

    @Bean
    public ViewResolver configureViewResolver() {
        InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
        viewResolve.setPrefix("/WEB-INF/pages/");
        viewResolve.setSuffix(".jsp");

        return viewResolve;
    }
}
