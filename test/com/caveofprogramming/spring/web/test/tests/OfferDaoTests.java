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
import javax.validation.constraints.AssertTrue;
import java.util.List;


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
        User user = new User("johnwpurcell", "ROLE_ADMIN", "John Purcell",  true, "john@cop.com", "hello");
        usersDao.create(user);
        Offer offer = new Offer(user, "This is a test offer");
        Offer offer2 = new Offer(user, "This is a second offer.");
        Assert.assertTrue("Offer creation should return true", offersDao.create(offer));
        Assert.assertTrue("Offer creation should return true", offersDao.create(offer2));

        List<Offer> offers = offersDao.getOffers();

        offer = offers.get(0);
        offer.setText("Updated offer text..");

        Assert.assertTrue("Offer update should return true", offersDao.update(offer));

        Assert.assertEquals("number of offers should be 1", 2, offers.size());
        Assert.assertTrue("Created offer should be identical to retrieved offer", offer.equals(offers.get(0)));

        Assert.assertEquals("Updated offer should match retrieved offer", offer, offersDao.getOffers().get(0));

        List<Offer> offersFromUser = offersDao.getOffers("johnwpurcell");
        Assert.assertEquals("There should be two total offers for this username", 2, offersFromUser.size());

    }

    @Test
    public void testOfferByUser() {
        System.out.println(offersDao.getOffers("johnwpurcell"));

    }
}

