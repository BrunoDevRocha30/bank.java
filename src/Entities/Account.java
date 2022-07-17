package Entities;

public class Account {
	private final double TAX = 5.0;
	
	private Integer number;
	private String holder;
	private double balance;
	
	public Account(Integer number, String holder) {
		this.number = number;
		this.holder = holder;
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
		balance -= amount + TAX;
	}
	
	public String toString() {
		return "\nAccount: "
				+ number
				+ "\nHolder: "
				+ holder
				+ "\nBalance: $"
				+ String.format("%.2f", balance);
	}
	
	
}