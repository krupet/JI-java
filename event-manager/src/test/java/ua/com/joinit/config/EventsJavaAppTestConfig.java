package ua.com.joinit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.mock.EventMockDAOImpl;
import ua.com.joinit.service.EventService;
import ua.com.joinit.service.impl.EventServiceImpl;

/**
 * Created by krupet on 10.06.2015.
 */

@Configuration
@EnableWebMvc
@ComponentScan("ua.com.joinit")
public class EventsJavaAppTestConfig {

    @Bean
    EventService eventService() {
        return new EventServiceImpl();
    }

    @Bean
    EventDAO eventDAO() {
        return new EventMockDAOImpl();
    }

    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}
