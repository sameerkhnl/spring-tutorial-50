package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;

@Service
public class OffersService {
    @Autowired
    private OffersDao offersDao;

    public List<Offer> getCurrent() {
        return offersDao.getOffers();
    }

    public Offer getOffer(int id) {
        return offersDao.getOffer(id);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void createOffer(Offer offer) {
        offersDao.saveOrUpdate(offer);
    }

    public boolean hasOffer(String username) {
        if (username == null) {
            return false;
        }

        return offersDao.getOffers(username).size() > 0;
    }

    public List<Offer> getOffers(String username) {
        if (username == null) {
            return null;
        }
        return offersDao.getOffers(username);
    }

    public Offer getOffer(String username) {
        return offersDao.getOffer(username);
    }

    public String createOrUpdate(Offer offer) {
        String returnValue = null;
        String username = getLoggedInUsername();
        if(offer.getId() == 0) {
            //offersDao.saveOrUpdate(offer);
            returnValue = "created";
        } else {
            returnValue = "updated";
        }
        if(getOffer(username) != null) {
            if(offer.getId() == getOffer(username).getId()) {
                try {
                    offersDao.saveOrUpdate(offer);
                } catch (Exception e) {
                    return returnValue = "couldnotcreateorupdate";
                }
            }
        }
        return returnValue;

    }

    public void delete(int id) {
        String username = getLoggedInUsername();
        if(getOffer(username) != null) {
            if(getOffer(username).getId() == id) {
                offersDao.delete(id);
            }
        }
    }

    public String getLoggedInUsername() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if(userDetails != null) {
            username = userDetails.getUsername();
            return username;
        }
        return null;
    }
}



