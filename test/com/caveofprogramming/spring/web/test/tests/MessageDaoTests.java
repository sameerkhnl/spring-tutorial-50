package com.caveofprogramming.spring.web.test.tests;

import com.caveofprogramming.spring.web.dao.*;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {"classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
        "classpath:com/caveofprogramming/spring/web/config/security-context.xml",
        "classpath:com/caveofprogramming/spring/web/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests {
    private JdbcTemplate template;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private OffersDao offersDao;

    @Autowired
    private MessagesDao messagesDao;

    @Before
    public void init() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.execute("delete from offers");
        template.execute("delete from messages");
        template.execute("delete from users");
    }

    @Test
    public void createMessage() {
        User johnwpurcell = new User("jkljklhlkhklh", "ROLE_ADMIN", "Jar Wagner", false, "jar.wagner85@example.com", "hello");
        usersDao.create(johnwpurcell);
        Message message = new Message("Test Subject1", "Test content1", "Isaac Newton", "isaac@caveofprogramming.com", johnwpurcell);
        messagesDao.saveOrUpdate(message);
    }

    @Test
    public void sendMessage() {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("khanal.sam91@gmail.com");
        mail.setTo("sameerkhnl@yahoo.com");
        mail.setSubject("test subject");
        mail.setText("test text");

        try {
            mailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot send message");
        }
    }


}
