package com.bank.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactionX")
public class Transaction {
	@Id
	private long transactionId;
	private LocalDate transactionDate;
	private double transactionAmount;
	private String transactionType;
	private String senderId;
	private String recieverId;
	
	public Transaction(double transactionAmount, String transactionType, String senderId, String recieverId ) {
		this.transactionAmount = transactionAmount;
		this.transactionDate = LocalDate.now();
		this.transactionId = (long) (Math.random()*10000);
		this.transactionType = transactionType;
		this.senderId = senderId;
		this.recieverId= recieverId;
	}
	
	public Transaction() {
		super();
	}

	public long getTransactionId() {
		return transactionId;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionDate=" + transactionDate
				+ ", transactionAmount=" + transactionAmount + ", transactionType=" + transactionType + ", senderId="
				+ senderId + ", recieverId=" + recieverId + "]";
	}
	
	

}
