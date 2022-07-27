package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Entities.Account;
import Entities.BusinessAccount;
import Entities.SavingsAccount;
import Exceptions.DomainException;

public class Program {

	public static void main(String[] args) {

		List<Account> account = new ArrayList<>();
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("How much will be the fee for withdraw: ");
		double tax = sc.nextDouble();
		System.out.print("How much will be the interest rate(for savings account income): ");
		double interestRate = sc.nextDouble();
		System.out.println();
		System.out.println("Now we need to instance a first account to begin... ");
		System.out.print("What type of account you want to instance?('B' Business or 'S' Savings): ");
		char type = sc.next().charAt(0);
		System.out.print("Enter The Account Number: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Account Holder: ");
		String holder = sc.nextLine();
		System.out.print("Is There An Initial Deposit? (y / n)");
		char response = sc.next().charAt(0);
		if (response == 'y' && type == 'b') {
			System.out.print("Enter The Initial Deposit Value: ");
			double initialDeposit = sc.nextDouble();
			System.out.print("Whats the loan limit for this account? ");
			double loanLimit = sc.nextDouble();
			account.add(new BusinessAccount(number, holder, initialDeposit, tax, loanLimit));
			} 
		else if(response == 'y' && type == 's'){
			System.out.print("Enter The Initial Deposit Value: ");
			double initialDeposit = sc.nextDouble();
			account.add(new SavingsAccount(number, holder, initialDeposit, interestRate));
			}
		System.out.println("Thanks For Choose Our Bank. Here's Your First Account Data:");
		System.out.println(account);

			do {
			System.out.println("Select an Operation...");
			System.out.println("[ 1 ] Instance a New Account");
			System.out.println("[ 2 ] Acess an Existing Account");
			System.out.println("[ 3 ] Close an Account");
			System.out.println("[ 0 ] Exit Program");
			number = sc.nextInt();			
			switch (number) {
			case 1:
				System.out.print("What type of account you want to instance?('B' Business or 'S' Savings): ");
				type = sc.next().charAt(0);
				System.out.print("Enter The Account Number: ");
				number = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter Account Holder: ");
				holder = sc.nextLine();
				System.out.print("Is There An Initial Deposit? (y / n)");
				response = sc.next().charAt(0);
				if (response == 'y' && type == 'b') {
					System.out.print("Enter The Initial Deposit Value: ");
					double initialDeposit = sc.nextDouble();
					System.out.print("Whats the loan limit for this account? ");
					double loanLimit = sc.nextDouble();
					account.add(new BusinessAccount(number, holder, initialDeposit, tax, loanLimit));
					} 
				else if(response == 'y' && type == 's'){
					System.out.print("Enter The Initial Deposit Value: ");
					double initialDeposit = sc.nextDouble();
					account.add(new SavingsAccount(number, holder, initialDeposit, interestRate));
					}
					System.out.println("Account Data:");
					System.out.println(account);
					break;
			case 2:
				System.out.println("List of existing accounts:");
					for (Account x : account) {
						System.out.println(x);
					}
					System.out.print("Type the number of the account you want to access: ");
					int accselect = sc.nextInt();
					Account selected = account.stream().filter(x -> x.getNumber() == accselect).findFirst().orElse(null);
					System.out.println("Account selected:");
					System.out.println(selected);
					int option;
					System.out.println();
						do {
							System.out.println("Select an operation:");
							System.out.println("[ 1 ] Deposit");
							System.out.println("[ 2 ] Withdraw");
							System.out.println("[ 3 ] Balance");
							System.out.println("[ 0 ] Exit Account");
							option = sc.nextInt();
							switch(option) {
							case 1:
								System.out.print("Enter a deposit value: ");
								double amount = sc.nextDouble();
								selected.deposit(amount);
								System.out.println("Updated Account data: " + selected);
								break;
							case 2:
								try {
								System.out.print("Enter a withdraw value: ");
								amount = sc.nextDouble();
								selected.withdraw(amount);
								}catch(DomainException e) {
									System.out.println(e.getMessage());
								}
								System.out.println("Updated Account data: " + selected);
								break;
							case 3:
								System.out.println("Account " + selected.getNumber() + " Balance is: " + selected.getBalance());
								break;
							case 0:
								break;
							}
						}while(option != 0);
					break;
			case 3:
				System.out.println("List of existing accounts:");
				for (Account x : account) {
					System.out.println(x);
				}
				System.out.print("Type the number of the account you want to close: ");
				int acclose = sc.nextInt();
				selected = account.stream().filter(x -> x.getNumber() == acclose).findFirst().orElse(null);
				System.out.println("Are you sure you want to close this account? (y / n)");
				System.out.println(selected);
				response = sc.next().charAt(0);
					if(response == 'y') {
						account.removeIf(x -> x.getNumber() == acclose);
					}else if(response == 'n') {
						System.out.println("Account closure " + selected.getNumber() + "was denied by user.");
						break;
					}
				}
			}while(number != 0);
		sc.close();
	}
}