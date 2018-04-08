package com.caveofprogramming.spring.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView handleDataAccessException(DataAccessException exception) {
		ModelAndView modelAndView = new ModelAndView("dataaccesserror");
		modelAndView.addObject("errMsg", exception.getMessage());
		modelAndView.addObject("exceptionClass:",exception.getClass());
		exception.printStackTrace();
		
		return modelAndView;
	}
//
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleGeneralException(Exception exception) {
//		ModelAndView modelAndView = new ModelAndView("dataaccesserror");
//		modelAndView.addObject("errMsg", exception.getMessage());
//		return modelAndView;
//	}

	@ExceptionHandler(AccessDeniedException.class)
	public ModelAndView handleAccessDeniedException(AccessDeniedException ex) {
		ModelAndView modelAndView = new ModelAndView("error");
		return modelAndView;
	}
}
