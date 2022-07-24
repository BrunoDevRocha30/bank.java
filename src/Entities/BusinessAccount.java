package Entities;

import Exceptions.DomainException;

public final class BusinessAccount extends Account{

	private Double tax;
	private Double loanLimit;
	
	public BusinessAccount() {
		super();
	}
	
	public BusinessAccount(Integer number, String holder, Double balance, Double tax, Double loanLimit) {
		super(number, holder, balance);
		this.tax = tax;
		this.loanLimit = loanLimit;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getLoanLimit() {
		return loanLimit;
	}

	public void setLoanLimit(Double loanLimit) {
		this.loanLimit = loanLimit;
	}
	
	@Override
	public void deposit(Double amount) {
		balance += amount;
	}
	
	@Override
	public void withdraw(Double amount) {
		balance -= amount + tax;
	}
	
	public void loan(Double loanValue) {
		balance += loanValue;
	}
	
	@Override
	public void validateWithdraw(double amount) {
		if(amount > tax + getBalance()) {
			throw new DomainException("Saldo insuficiente");
		}
	}
}
