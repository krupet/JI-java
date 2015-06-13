package ua.com.joinit.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.com.joinit.dao.config.TestOrmConfig;

/**
 * Created by krupet on 15.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestOrmConfig.class)
//@TransactionConfiguration(defaultRollback = true)
//@Transactional
public abstract class DAOBaseAppTest {
}
