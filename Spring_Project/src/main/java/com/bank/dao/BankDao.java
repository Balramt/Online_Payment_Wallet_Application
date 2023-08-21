package com.bank.dao;

import java.util.List;

import com.bank.exception.UserException;
import com.bank.model.Transaction;
import com.bank.model.User;

public interface BankDao {
	public int createAccount(User user) throws UserException;
	public int checkCredentials(String emailId,String password) throws UserException;
	public  User readAccount(int accountNo) throws UserException;
	public boolean deposite(int accountNo, double amount) throws UserException;
	public boolean withdrawl(int accountNo, double amount) throws UserException;
	public boolean fundTransfer(int senderAccountNo, int recieverAccountNo, double amount) throws UserException;
	public List<Transaction> getTransactionList(int accountNo) throws UserException;
	public void addTransaction(User user, double transactionAmount, String transactionType, String senderId, String recieverId);
	public abstract void commitTransaction();
	public abstract void beginTransaction();
}
