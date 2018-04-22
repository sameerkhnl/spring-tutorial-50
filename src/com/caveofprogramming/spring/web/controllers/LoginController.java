package com.caveofprogramming.spring.web.controllers;

import javax.validation.Valid;

import com.caveofprogramming.spring.web.dao.FormValidationGroup;
import com.caveofprogramming.spring.web.dao.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.service.UsersService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private MailSender mailSender;

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
    public ModelAndView accountCreated(@Validated(FormValidationGroup.class) User user, BindingResult result) {
        ModelAndView mView = new ModelAndView();
        if (result.hasErrors()) {
            System.out.println("cannot saveOrUpdate a new account");
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


    @RequestMapping(value = "/getmessages", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map<String, Object> getMessages(Principal principal) {
        List<Message> messages = null;

        if (principal == null) {
            messages = new ArrayList<>();
        } else {
            messages = usersService.getMessages(principal.getName());
        }

        Map<String, Object> data = new HashMap<>();
        data.put("messages", messages);
        data.put("number", messages.size());

        return data;
    }

    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> sendMessages(Principal principal, @RequestBody Map<String, Object> data) {
        String text = (String)data.get("text");
        String name = (String)data.get("name");
        String email = (String)data.get("email");
        String subject = (String)data.get("subject");
        Integer id = (Integer)data.get("id");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("khanal.sam91@gmail.com");
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setText(text);

        try {
            mailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot send message");
        }

        Map<String, Object> rval = new HashMap<>();
        rval.put("success", true);
        rval.put("target", id);

        System.out.println(text);

        return rval;

    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String viewMessages() {
        return "messages";
    }
}
















