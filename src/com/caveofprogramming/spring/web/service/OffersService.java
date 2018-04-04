package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void createOffer(Offer offer) {
		offersDao.create(offer);
	}

	public Offer throwNewException() {
		return offersDao.getOffer(9999);
	}
}
