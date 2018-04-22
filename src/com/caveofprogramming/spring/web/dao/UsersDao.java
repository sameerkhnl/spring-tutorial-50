package com.caveofprogramming.spring.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository("usersDao")
@Transactional
public class UsersDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsersDao() {
        System.out.println("usersDao instantiated");
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List<User> getUsers() {
        CriteriaBuilder builder = session().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("username"), "johnwpurcell"));

        return session().createQuery(query).getResultList();
    }

    public User getUser(String username) {

        CriteriaBuilder builder = session().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("username"), username));
        return session().createQuery(query).uniqueResult();
    }

    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session().persist(user);
    }

    public void create(List<User> users) {
        for(User user: users) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            session().persist(user);
        }
    }




    public void update(User user) {
        session().update(user);
    }

    public boolean exists(String username) {
        return getUser(username) != null;
    }

}
