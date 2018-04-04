package com.caveofprogramming.spring.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DatabaseErrorHandler {
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
}
