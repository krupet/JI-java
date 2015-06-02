package ua.com.joinit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.com.joinit.dao.UserDAO;
import ua.com.joinit.mock.UserMockDAOImpl;
import ua.com.joinit.service.UserService;
import ua.com.joinit.service.impl.UserServiceImpl;

/**
 * Created by krupet on 28.01.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.com.joinit")
@ImportResource({"classpath:app-test-config.xml"})
public class UsersJavaAppTestConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserDAO userMockDAO() {
        return new UserMockDAOImpl();
    }

    @Bean
    public ViewResolver configureViewResolver() {
        InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
        viewResolve.setPrefix("/WEB-INF/pages/");
        viewResolve.setSuffix(".jsp");

        return viewResolve;
    }

    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}
