package ua.com.joinit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.joinit.config.EventsJavaAppTestConfig;

/**
 * Created by krupet on 25.02.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = EventsJavaAppTestConfig.class)
public abstract class EventManagerBaseAppTest {
}
