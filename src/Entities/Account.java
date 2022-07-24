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
		balance -= amount;
	}
		
	public void validateWithdraw(double amount) {
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