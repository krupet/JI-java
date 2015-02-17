package ua.com.joinit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.com.joinit.service.UserCRUDService;
import ua.com.joinit.service.UserService;
import ua.com.joinit.service.impl.UserCRUDServiceImpl;
import ua.com.joinit.service.impl.UserServiceImpl;

/**
 * Created by krupet on 28.01.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.com.joinit")
@ImportResource({"classpath:app-config.xml"})
public class UsersJavaAppConfig {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserCRUDService userCRUDService() {
        return new UserCRUDServiceImpl();
    }

    @Bean
    public ViewResolver configureViewResolver() {
        InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
        viewResolve.setPrefix("/WEB-INF/pages/");
        viewResolve.setSuffix(".jsp");

        return viewResolve;
    }
}
