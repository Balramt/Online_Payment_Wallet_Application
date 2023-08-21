package com.bank.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="userX",
uniqueConstraints={@UniqueConstraint(columnNames={"mobileNo", "emailId"})}
		)
public class User{
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_JUST_FOR_TEST", initialValue=1000, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	private int accountNo;
	private String userName;
	private Long mobileNo;
	private String emailId;
	private double balance =1000 ;
	private String password;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Transaction> transactionList = new ArrayList<Transaction>();
	public User(String userName, Long mobileNo, String emailId,String password) {
		this.userName = userName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.password = password;
	}
	
	
	

	public User() {
		super();
	}




	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "User [accountNo=" + accountNo + ", userName=" + userName + ", mobileNo=" + mobileNo + ", emailId="
				+ emailId + ", balance=" + balance + "]";
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void addTransactionList(Transaction transaction) {
		transactionList.add(transaction); 
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	


}
