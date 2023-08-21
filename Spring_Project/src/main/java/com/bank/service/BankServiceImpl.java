package com.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.BankDao;
import com.bank.exception.UserException;
import com.bank.model.Transaction;
import com.bank.model.User;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDao dao;

	public int createAccount(User user) throws UserException {
		dao.beginTransaction();
		int accountNo = dao.createAccount(user);
		dao.commitTransaction();
		return accountNo;
	}

	public boolean deposite(int accountNo, double amount) throws UserException {
		if (amount > 0) {
			dao.beginTransaction();
			boolean result = dao.deposite(accountNo, amount);
			dao.commitTransaction();
			return result;
		} else {
			throw new UserException("amount can not be negative");
		}
	}

	public boolean withdrawl(int accountNo, double amount) throws UserException {
		if (amount > 0) {
			dao.beginTransaction();
			boolean result = dao.withdrawl(accountNo, amount);
			dao.commitTransaction();
			return result;
		} else {
			throw new UserException("Amount must be positive");
		}
	}

	public boolean fundTransfer(int senderAccountNo, int recieverAccountNo, double amount) throws UserException {
		if (amount > 0) {
			dao.beginTransaction();
			boolean result = dao.fundTransfer(senderAccountNo, recieverAccountNo, amount);
			dao.commitTransaction();
			return result;
		} else {
			throw new UserException("Please Enter Valid amount");
		}
	}

	public List<Transaction> printTransaction(int accountNo) throws UserException {
		dao.beginTransaction();
		List<Transaction> list = dao.getTransactionList(accountNo);
		dao.commitTransaction();
		return list;
	}

	public double getBalance(int accountNo) throws UserException {
		dao.beginTransaction();
		User user = dao.readAccount(accountNo);
		dao.commitTransaction();

		return user.getBalance();
	}

	public boolean IsNumber(int accountNo) {
		if (accountNo == (int) accountNo) {
			return true;
		}
		return false;
	}

	public boolean IsEmail(String email) {

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if( email.matches(regex)){
			return true;
		}
		else{
			throw new UserException("Invalid email Address");
		}

	}

	public boolean IsMobileNo(Long mobileNo) {
		if (mobileNo == (long) mobileNo && mobileNo.toString().length() == 10) {
			return true;
		}
		else{
			throw new UserException("Invalid Mobile Number and Length must of 10 Digits");
		}
		//return false;

	}

	public boolean IsAlpha(String str) {
		String regex = "^[a-zA-Z\\s]*$";
		if( str.matches(regex)){
			return true;
		}
		else{
			throw new UserException("Name must Contains Only Alphabets");
		}
	}

	public int login(String emailId, String password) throws UserException {

		return dao.checkCredentials(emailId, password);
	}
}
