package com.bank.service;

import java.util.List;

import com.bank.exception.UserException;
import com.bank.model.Transaction;
import com.bank.model.User;

public interface BankService {
	public int createAccount(User user) throws UserException;
	public int login(String emailId,String password) throws UserException;
	public double getBalance(int accountNo) throws UserException;
	public boolean deposite(int accountNo, double amount) throws UserException;
	public boolean withdrawl(int accountNo, double amount) throws UserException;
	public boolean fundTransfer(int senderAccountNo, int recieverAccountNo, double amount) throws UserException;
	public List<Transaction> printTransaction(int accountNo) throws UserException;
	public boolean IsAlpha(String str);
	public boolean IsNumber(int accountNo);
	public boolean IsEmail(String email);
	public boolean IsMobileNo(Long mobileNo);


}
