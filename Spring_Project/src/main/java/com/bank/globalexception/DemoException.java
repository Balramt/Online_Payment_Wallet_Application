package com.bank.globalexception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.dao.BankDao;

@ControllerAdvice
public class DemoException {
	
	@Autowired
	private BankDao dao;
	@ResponseBody
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {Exception.class})
    protected ErrorInfo handleConflict(Exception ex, HttpServletRequest req) {
        String bodyOfResponse = ex.getMessage();// "Country with this id not present";
        String uri = req.getRequestURL().toString();
        dao.commitTransaction();
        return  new ErrorInfo(uri,bodyOfResponse);
    }
}
