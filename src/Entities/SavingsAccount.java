package Entities;

import Exceptions.DomainException;

public final class SavingsAccount extends Account {

	private Double interestRate;
	
	public SavingsAccount() {
		super();
	}

	public SavingsAccount(Integer number, String holder, Double balance, Double interestRate) {
		super(number, holder, balance);
		this.interestRate = interestRate;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	
	@Override
	public  void deposit(Double amount) {
		balance += amount;
	}
	
	@Override
	public void withdraw(Double amount) {
		validateWithdraw(amount);
		balance -= amount;
	}
	
	@Override
	protected void validateWithdraw(double amount) {
		if(amount > getBalance()) {
			throw new DomainException("Saldo insuficiente");
		}
	}
	
	public void interestRateUpdate() {
		balance += (balance * interestRate);
	}
}
