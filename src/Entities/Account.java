package Entities;

import Exceptions.DomainException;

public class Account {
	
	private Integer number;
	private String holder;
	protected double balance;
	
	public Account() {
	}

	public Account(Integer number, String holder, Double balance) {
		this.number = number;
		this.holder = holder;
		this.balance = balance;
	}

	public Integer getNumber() {
		return number;
	}
	
	protected void validateTax(double tax) {
		if(tax > 100.0) {
			throw new DomainException("Withdraw fee must be less than $10.0");
		}
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public Double getBalance() {
		return balance;
	}
	
	public void deposit(Double amount) {
		balance += amount;
	}
	
	public void withdraw(Double amount) {
		validateWithdraw(amount);
		balance -= amount;
	}
			
	protected void validateWithdraw(double amount) {
		if(amount > getBalance()) {
			throw new DomainException("Saldo insuficiente");
		}
	}
	
	public String toString() {
		return "Account: "
				+ number
				+ " Holder: "
				+ holder
				+ " Balance: $"
				+ String.format("%.2f", balance);
	}
	
	
}