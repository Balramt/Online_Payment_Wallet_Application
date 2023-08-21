package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.exception.UserException;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.service.BankService;

@RestController
@RequestMapping(value ="bank")
@CrossOrigin(origins="http://localhost:4200")
public class BankRestCotroller {

	@Autowired
	private BankService service;
	
	
	
	// http://localhost:9090/bank/hello
	@RequestMapping(value = "/hello", method = RequestMethod.DELETE)
	public String helloWorld() {
		System.out.println("Printing");
		return "Hello from Rest Controller method is called";

	}

	// http://localhost:9090/bank/create/balram/9911258164/balramt02@gmail.com/123
	@RequestMapping(value = "/create/{name}/{mobileNo}/{email}/{password}", method = RequestMethod.POST)
	public int createAccount(@PathVariable(name = "name") String name, @PathVariable Long mobileNO,
			@PathVariable String email, @PathVariable String password) {
		User user = new User(name, mobileNO, email, password);
		int accountNo = service.createAccount(user);
		return accountNo;

	}

	//http://localhost:9090/bank/create/
	@RequestMapping(value = "/create/", method = RequestMethod.POST)
	public boolean createAccount(@RequestBody User user) {
		String userName = user.getUserName();
		long mobileNo = user.getMobileNo();
		String emailId = user.getEmailId();
		if (service.IsAlpha(userName) && service.IsMobileNo(mobileNo) && service.IsEmail(emailId)) {

			int accountNo = service.createAccount(user);
			return true;
		} else {
			throw new UserException("Please Enter Valid Input");
		}
	}

	//http://localhost:9090/bank/login/balramt02@gmail.com/123
	@RequestMapping(value = "login/{emailId}/{password}", method = RequestMethod.GET)
	public int login(@PathVariable("emailId") String emailId, @PathVariable("password") String password) {
		int accountNo = service.login(emailId, password);
		return accountNo;
	}

	//http://localhost:9090/bank/login/getBalance/21
	@RequestMapping(value = "login/getBalance/{accountNo}", method = RequestMethod.GET)
	public double getBalance(@PathVariable("accountNo") int accountNo) {
		return service.getBalance(accountNo);
	}

	// http://localhost:9090/bank/login/deposite/21/500
	@RequestMapping(value = "login/deposite/{accountNo}/{amount}", method = RequestMethod.GET)
	public boolean deposite(@PathVariable("accountNo") int accountNo, @PathVariable("amount") double amount) {
		return service.deposite(accountNo, amount);
	}

	//http://localhost:9090/bank/login/withdrawl/21/500
	@RequestMapping(value="login/withdrawl/{accountNo}/{amount}",method=RequestMethod.GET)
	public boolean withdrawl(@PathVariable("accountNo") int accountNo, @PathVariable("amount") double amount){
		return service.withdrawl(accountNo, amount);
	}
	
	//http://localhost:9090/bank/login/fundTransfer/21/23/100
	@RequestMapping(value="login/fundTransfer/{senderAccountNo}/{recieverAccountNo}/{amount}",method=RequestMethod.GET)
	public boolean fundTransfer(@PathVariable("senderAccountNo") int senderAccountNo,@PathVariable("recieverAccountNo") int recieverAccountNo,@PathVariable("amount") double amount){
		return service.fundTransfer(senderAccountNo, recieverAccountNo, amount);
	}
	
	//http://localhost:9090/bank/login/list/21
	@RequestMapping(value="login/list/{accountNo}",method=RequestMethod.GET)
	public List<Transaction> getAllTransactionList(@PathVariable("accountNo") int accountNo){
		return service.printTransaction(accountNo);
	}
	
	
	
	
}
