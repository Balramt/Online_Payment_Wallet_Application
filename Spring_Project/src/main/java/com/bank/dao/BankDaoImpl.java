package com.bank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bank.exception.UserException;
import com.bank.model.Transaction;
import com.bank.model.User;

@Repository
public class BankDaoImpl implements BankDao {
	private EntityManager entityManager;
	private User currentUser;

	public BankDaoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}

	public int createAccount(User user) throws UserException {
		TypedQuery<User> query = entityManager
				.createQuery("select u from User u where u.emailId=:email or u.mobileNo=:mob", User.class);
		query.setParameter("email", user.getEmailId());
		query.setParameter("mob", user.getMobileNo());
		List<User> user1 = query.getResultList();
		System.out.println(user1);
		if (user1.isEmpty()) {
			System.out.println("hjghjgcxcvb");
			entityManager.persist(user);
			return user.getAccountNo();
		} else {
			entityManager.getTransaction().commit();
			throw new UserException("Mobile Number and EmailId is already exist");
		}
	}
////////////////////////////////////////////////////////////////////////////////////////
	public int checkCredentials(String emailId, String password) throws UserException {
		TypedQuery<User> query = entityManager
				.createQuery("select u from User u where u.emailId=:email and u.password=:pass", User.class);
		query.setParameter("email", emailId);
		query.setParameter("pass", password);
		List<User> user1 = query.getResultList();
		if(user1.isEmpty()){
			throw new UserException("Invalid Credentials!!");
		}else{
			User user = user1.get(0);
			currentUser = user;
			return user.getAccountNo();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
	public User readAccount(int accountNo) throws UserException {

		User user = entityManager.find(User.class, accountNo);
		if (user == null) {
			throw new UserException("Invalid Account number");
		} else {
			return user;
		}
	}
//////////////////////////////////////////////////////////////////////////////////
	public boolean deposite(int accountNo, double amount) throws UserException {
	//	double a = currentUser.getBalance();
		User currentUser = readAccount(accountNo);
		double a = currentUser.getBalance();
		String acc = Integer.toString(accountNo);
		a = a + amount;
		currentUser.setBalance(a);
		addTransaction(currentUser, amount, "Deposite", "By Self", acc);
		entityManager.persist(currentUser);

		return true;
	}
//////////////////////////////////////////////////////////////////////////////////
	public boolean withdrawl(int accountNo, double amount) throws UserException {
	//	double a = currentUser.getBalance();
		User currentUser = readAccount(accountNo);
		double a = currentUser.getBalance();
		String acc = Integer.toString(accountNo);
		if (a >= amount) {
			a = a - amount;
			currentUser.setBalance(a);
			entityManager.persist(currentUser);
			addTransaction(currentUser, amount, "Withdrawl", acc, "By Self");
			return true;
		} else {
			throw new UserException("Insufficient Balance!!!!");
		}
	}
///////////////////////////////////////////////////////////////////////////////////
	public boolean fundTransfer(int senderAccountNo, int recieverAccountNo, double amount) throws UserException {
		//double senderAmount = currentUser.getBalance();
		User currentUser = readAccount(senderAccountNo);
		double senderAmount = currentUser.getBalance();
		User recieverUser = readAccount(recieverAccountNo);
		double recieverAmount = recieverUser.getBalance();
		if (senderAmount < amount) {
			throw new UserException("Insufficient Balance");
		} else {
			senderAmount = senderAmount - amount;
			currentUser.setBalance(senderAmount);
			recieverAmount = recieverAmount + amount;
			recieverUser.setBalance(recieverAmount);
			String depositorId = Integer.toString(senderAccountNo);
			String recieverId = Integer.toString(recieverAccountNo);

			entityManager.persist(currentUser);
			addTransaction(currentUser, amount, "FundTransfer", depositorId, recieverId);

			entityManager.persist(recieverUser);
			addTransaction(recieverUser, amount, "FundTransfer", recieverId, depositorId);
			return true;
		}

	}
///////////////////////////////////////////////////////////////////////////////////////
	public List<Transaction> getTransactionList(int accountNo) throws UserException {
		User currentUser = readAccount(accountNo);
		List<Transaction> tList = currentUser.getTransactionList();

		return tList;
	}
/////////////////////////////////////////////////////////////////////////////////////
	public void addTransaction(User user, double transactionAmount, String transactionType, String senderId,
			String recieverId) {
		Transaction t = new Transaction(transactionAmount, transactionType, senderId, recieverId);
		user.addTransactionList(t);
		entityManager.merge(user);
	}
//////////////////////////////////////////////////////////////////////////////////////
	public void commitTransaction() {
		entityManager.getTransaction().commit();

	}
//////////////////////////////////////////////////////////////////////////////////////
	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}

}
