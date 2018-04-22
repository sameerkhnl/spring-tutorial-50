package com.caveofprogramming.spring.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.service.OffersService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OffersController {
    @Autowired
    private OffersService offersService;

    @RequestMapping("/offers")
    public String showOffers(Model model, Principal principal) {
        List<Offer> offers = offersService.getCurrent();
        boolean hasOffer = offersService.hasOffer(principal.getName());
        //offersService.throwNewException();
        model.addAttribute("hasOffer", hasOffer);
        model.addAttribute("offers", offers);

        return "offers";

    }

    @RequestMapping("/createoffer")
    public String createOffer(Model model, Principal principal) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        Offer offer = offersService.getOffer(username);
        if (offer == null) {
            model.addAttribute("offer", new Offer());
        } else {
            model.addAttribute("offer", offer);
        }
        return "createoffer";
    }

    @RequestMapping(value = "docreate", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid Offer offer, BindingResult result, Principal principal, @RequestParam
            (value = "delete", required = false) String delete) {
        if (result.hasErrors()) {
            System.out.println("form could not be validated");
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            return "createoffer";
        }
        offer.getUser().setUsername(principal.getName());

        if (delete != null) {
            offersService.delete(offer.getId());
            return "offerdeleted";
        }

        if(offer.getId() == 0) {
            model.addAttribute("action", "created");
        } else {
            model.addAttribute("action", "updated");
        }
        offersService.createOffer(offer);

        return "offercreated";
    }
}
