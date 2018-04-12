package com.caveofprogramming.spring.web.test.tests;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

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
    private UsersDao usersDao;

    @Autowired
    private OffersDao offersDao;

    @Before
    public void init() {
        this.template = new JdbcTemplate(dataSource);
        template.execute("delete from offers");
        template.execute("delete from users");
    }

    @Test
    public void testUsers() {
        User user = new User("johnwpurcell", "ROLE_ADMIN", "John Purcell",  true, "john@cop.com", "hello");
        Assert.assertTrue("User created successfully", usersDao.create(user));

        List<User> users = usersDao.getUsers();

        Assert.assertEquals("number of users should be 1", 1, users.size());
        Assert.assertTrue("User should exist", usersDao.exists(user.getUsername()));
        Assert.assertEquals("Created user should be identical to retrieved user", true, user.equals(users.get(0)));
        Assert.assertTrue("User should be enabled", users.get(0).isEnabled());
        user.setEnabled(false);

        Assert.assertFalse("Retrieved user should not be equal to created user", user.equals(usersDao.getUsers().get(0)));
        Assert.assertEquals("Username should be johnwpurcell", "johnwpurcell", usersDao.getUser("johnwpurcell").getUsername());
        //System.out.println(usersDao.getUser("johnwpurcell").isEnabled());
        usersDao.update(user);
        Assert.assertFalse("User should now be disabled", usersDao.getUser("johnwpurcell").isEnabled());

        usersDao.delete("johnwpurcell");
        Assert.assertEquals("There should be no user in the db now", 0, usersDao.getUsers().size());
    }

}
