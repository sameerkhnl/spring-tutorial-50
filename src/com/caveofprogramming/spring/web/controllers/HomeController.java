package com.caveofprogramming.spring.web.controllers;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;
import com.caveofprogramming.spring.web.service.OffersService;
import org.apache.log4j.Logger;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private static Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private OffersService offersService;

    @RequestMapping("/")
    public ModelAndView showHome(Principal principal) {
        logger.info("Showing home page...");
        List<Offer> offers = offersService.getCurrent();
        ModelAndView modelAndView = new ModelAndView("home");

        String username = null;
        if(principal != null) {
            username = principal.getName();
        }
        modelAndView.addObject("offers", offers).addObject("hasOffer", offersService.hasOffer(username));
        return modelAndView;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showTest(Model model, @RequestParam(value = "id", required = false) String id) {

        System.out.println("The id is: " + id);

        return "home";
    }
}
