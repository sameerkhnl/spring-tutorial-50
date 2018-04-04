package com.caveofprogramming.spring.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class LoginController {
	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginForm(@RequestParam(value = "error", required = false) Boolean error) {
		ModelAndView mView = new ModelAndView("login");
		return mView;
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
		} else if(usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			mView.setViewName("createaccount"); 
		} else {
			mView.setViewName("accountcreated");
			user.setEnabled(true);
			user.setAuthority("user");
			usersService.createUser(user);
		}

		return mView;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleGeneralException(Exception exception) {
		ModelAndView modelAndView = new ModelAndView("dataaccesserror");
		modelAndView.addObject("errMsg", exception.getMessage());
		return modelAndView;
	}
}
