package com.caveofprogramming.spring.web.test.tests;

import com.caveofprogramming.spring.web.dao.OffersDao;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import javax.validation.Validator;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {"classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
        "classpath:com/caveofprogramming/spring/web/config/security-context.xml",
        "classpath:com/caveofprogramming/spring/web/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

    private JdbcTemplate template;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private OffersDao offersDao;

    private static Validator validator;

    @Before
    public void init() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.execute("delete from offers");
        template.execute("delete from users");
        //ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        //validator = factory.getValidator();
    }

    @Test
    public void testUsers() {
        User user1 = new User("jkljklhlkhklh", "ROLE_ADMIN", "Jar Wagner", true, "jar.wagner85@example.com", "hello");

        //validator.validate(user1, PersistenceValidationGroup.class);

        usersDao.create(user1);

        //user1.setUsername("f");
        //usersDao.update(user1);
        System.out.println(usersDao.getUsers());
    }

    @Test
    public void createRetrieve() {
        User user1 = new User("jkljklhlkhklh", "ROLE_ADMIN", "Jar Wagner", true, "jar.wagner85@example.com", "hello");
        usersDao.create(user1);
        System.out.println(usersDao.getUser("jkljklhlkhklh"));
    }

}
