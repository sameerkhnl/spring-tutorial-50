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
import java.util.Arrays;


@ActiveProfiles("dev")
@ContextConfiguration(locations = {"classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
        "classpath:com/caveofprogramming/spring/web/config/security-context.xml",
        "classpath:com/caveofprogramming/spring/web/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

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
    public void testOffers() {
        User user = new User("johnwpurcell", "ROLE_ADMIN", "John Purcell", true, "john@cop.com", "hello");
        User user2 = new User("Jonasjonassss", "ROLE_USER", "JonasJonas", false, "jonas@cop.com", "hello");
        usersDao.create(user);
        usersDao.create(user2);

        Offer offer = new Offer(user, "New offer from john");
        Offer offer2 = new Offer(user2, "Offer from JonasJonas");

        offersDao.create(Arrays.asList(offer, offer2));

        Assert.assertEquals("No. of offers should be 1", 1, offersDao.getOffers("johnwpurcell").size());
        Assert.assertEquals("No. of offers should be 0", 0, offersDao.getOffers("asdf").size());
        offer2.setText("This offer has updated text");
        user2.setEnabled(true);
        usersDao.update(user2);
        offersDao.saveOrUpdate(offer2);
        Offer retrieved = offersDao.getOffer(user2.getUsername());
        Assert.assertEquals("This offer has updated text", retrieved.getText());
        offersDao.delete(retrieved.getId());
        Assert.assertEquals("No. of offers should be 1", 1, offersDao.getOffers().size());
        Assert.assertEquals("The retrieved offer should have null id", null, offersDao.getOffer(offer2.getUsername()));
    }

    @Test
    public void testOfferByUser() {
        System.out.println(offersDao.getOffers("johnwpurcell"));

    }
}

