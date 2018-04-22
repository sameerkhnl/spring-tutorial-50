package com.caveofprogramming.spring.web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("messagesDao")
@Transactional
public class MessagesDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public MessagesDao() {
        System.out.println("MessagesDao instantiated");
    }

    public void saveOrUpdate(Message message) {
        System.out.println("saving message into the db");
        session().saveOrUpdate(message);
    }

    public List<Message> getMessages() {
        return session().createQuery("select m from Message m inner join m.user where m.user.enabled = true", Message
                .class).getResultList();
    }

    public List<Message> getMessages(String username) {
        return session().createQuery("select m from Message m inner join m.user where m.user.enabled = true and m" +
                ".user.username = :username", Message.class).setParameter("username", username).getResultList();
    }

}
