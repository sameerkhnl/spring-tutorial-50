package com.caveofprogramming.spring.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.service.UsersService;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginForm(@RequestParam(value = "error", required = false) Boolean error) {
        ModelAndView mView = new ModelAndView("login");
        return mView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView showAdminPage() {
        List<User> users = usersService.getUsers();
        return new ModelAndView("admin", "users", users);
    }

    @RequestMapping(value = "/loggedout", method = RequestMethod.GET)
    public ModelAndView showLoggedOut() {
        ModelAndView modelAndView = new ModelAndView("loggedout");
        return modelAndView;

    }

    @RequestMapping(value = "/newaccount", method = RequestMethod.GET)
    public String showCreateAccountForm(Model model) {
        model.addAttribute("user", new User());
        return "createaccount";
    }

    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
    public ModelAndView accountCreated(@Valid User user, BindingResult result) {
        ModelAndView mView = new ModelAndView();
        if (result.hasErrors()) {
            System.out.println("cannot create a new account");
            mView.setViewName("createaccount");
        } else if (usersService.exists(user.getUsername())) {
            result.rejectValue("username", "DuplicateKey.user.username");
            mView.setViewName("createaccount");
        } else {
            mView.setViewName("accountcreated");
            user.setEnabled(true);
            user.setAuthority("ROLE_ADMIN");
            usersService.createUser(user);
        }

        return mView;
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public ModelAndView showAccessDeniedPage() {
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }
}
