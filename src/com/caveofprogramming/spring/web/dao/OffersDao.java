package com.caveofprogramming.spring.web.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("offersDao")
@Transactional
public class OffersDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public OffersDao() {
        System.out.println("OffersDao instantiated");
    }


    public List<Offer> getOffers() {
        return session().createQuery("select o from Offer o inner join o.user where o.user.enabled = true", Offer
                .class).getResultList();
    }

    public Offer getOffer(int id) {
        return session().get(Offer.class, id);
    }

    public void delete(int id) {
        session().delete(getOffer(id));
    }

    public void create(List<Offer> offers) {
        for (Offer offer : offers) {
            session().persist(offer);
        }
    }

    public void saveOrUpdate(Offer offer) {
        session().saveOrUpdate(offer);
    }

    public List<Offer> getOffers(String username) {
        Query query = session().createQuery("select o from Offer o inner join o.user where o.user.username = " +
                ":username and o.user.enabled = true", Offer.class).setParameter("username", username);

        return query.getResultList();
    }

    public Offer getOffer(String username) {
        return (Offer) session().createQuery("select o from Offer o inner join o.user where o.user.username = " +
                ":username and o.user.enabled = true").setParameter("username", username).setMaxResults(1)
                .uniqueResult();
    }

}
