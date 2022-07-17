package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Entities.Account;

public class Program {
	
	private static final double TAX = -5.00;

	public static void main(String[] args) {
		
		List <Account> list = new ArrayList<>();
		Locale.setDefault(Locale.US);
		int number, op;
		Account account = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME TO OUR BANK MANAGER ... ");
		System.out.println("");
		System.out.println("Hello User! First We Need to Instance a New Account To Begin... ");
		
			System.out.println("");
			System.out.print("Enter The Account Number: ");
			number = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter Account Holder: ");
			String holder = sc.nextLine();
			System.out.print("Is There An Initial Deposit? ( Y / N )");
			char response = sc.next().charAt(0);
			
			if(response == 'y') {
				System.out.print("Enter The Initial Deposit Value: ");
				double initialDeposit = sc.nextDouble();
				account = new Account(number, holder, initialDeposit);
				list.add(account);
				}else {
					account = new Account(number, holder);
					list.add(account);
					}
			System.out.println("Thanks For Choose Our Bank. Here's Your First Account Data:");
			System.out.println(account);
			
		do {
			System.out.println("");
			System.out.println("Select an Operation...");
			System.out.println("[ 1 ] Instance a New Account");
			System.out.println("[ 2 ] Acess an Existing Account");
			System.out.println("[ 3 ] Close an Account");
			System.out.println("[ 0 ] Exit Program");
			number = sc.nextInt();
			
		switch(number) {
			case 1:
				System.out.print("Enter The Account Number: ");
				number = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter Account Holder: ");
				holder = sc.nextLine();
				System.out.print("Is There An Initial Deposit? ( Y / N )");
				response = sc.next().charAt(0);
				
				if(response == 'y') {
					System.out.print("Enter The Initial Deposit Value: ");
					double initialDeposit = sc.nextDouble();
					account = new Account(number, holder, initialDeposit);
					list.add(account);
					}else {
						account = new Account(number, holder);
						list.add(account);
						}
				System.out.println("Account Data:");
				System.out.println(account);
				
				do {
				System.out.println(" ");
				System.out.println("What Do You Want To Do Now?");
				System.out.println("[ 1 ] Depósit");
				System.out.println("[ 2 ] Withdraw");
				System.out.println("[ 3 ] See My Balance");
				System.out.println("[ 0 ] Exit Account");
				op = sc.nextInt();
				
				if(op==0)
					break;
				
				while(op < 0 && op > 3) {
					System.out.println("Incorrect Value");
					op = sc.nextInt();
				}
				
				switch(op) {
					case 1:
						System.out.print("Enter a Deposit Value: ");
						double amount = sc.nextDouble();
						account.deposit(amount);
						System.out.println("Updated Account Data:");
						System.out.println(account);
						break;
					case 2:
						System.out.println("A Tax Value For Withdraw Will Be Charged: tax Value: $5.00");
						System.out.print("Enter a Withdraw Value: ");
						amount = sc.nextDouble();
							if(amount > (account.getBalance() + TAX)) {
								System.out.println("You Don't Have Enough Balance!");
								break;
							}else {
								account.withdraw(amount);
								System.out.println("Updated Account Data:");
								System.out.println(account);
								break;
							}
					case 3:
						System.out.printf("Your Current Balance: %.2f%n", account.getBalance());
						break;
				}
				}while(op!=0);
				
				System.out.println(" ");
				break;
				
			case 2:
				System.out.println("List of Exixting Accounts. Choose one of them...");
				for(Account x : list) {
					System.out.println(x);
				}
				System.out.print("Set The Account Number Do You Want To Acess... ");
				int accnum = sc.nextInt();
				Account selected = list.stream().filter(x -> x.getNumber() == accnum).findFirst().orElse(null);
				if (selected == null) {
					System.out.println("This Account does not exist!");
				}
				else {
					System.out.println("Selected Account: " + selected);
					do {
						System.out.println(" ");
						System.out.println("What Do You Want To Do Now?");
						System.out.println("[ 1 ] Depósit");
						System.out.println("[ 2 ] Withdraw");
						System.out.println("[ 3 ] See My Balance");
						System.out.println("[ 0 ] Exit Account");
						op = sc.nextInt();
						
						if(op==0)
							break;
						
						if(op < 0 && op >3) {
							System.out.println("Incorrect Value");
							op = sc.nextInt();
						}
						
						switch(op) {
							case 1:
								System.out.print("Enter a Deposit Value: ");
								double amount = sc.nextDouble();
								selected.deposit(amount);
								System.out.println("Updated Account Data:");
								System.out.println(selected);
								break;
							case 2:
								System.out.println("A Tax Value For Withdraw Will Be Charged: tax Value: $5.00");
								System.out.print("Enter a Withdraw Value: ");
								amount = sc.nextDouble();
									if(amount > (selected.getBalance() + TAX)) {
										System.out.println("You Don't Have Enough Balance!");
										break;
									}else {
										selected.withdraw(amount);
										System.out.println("Updated Account Data:");
										System.out.println(selected);
										break;
									}
							case 3:
								System.out.printf("Your Current Balance: %.2f%n", selected.getBalance());
								break;
						}
						}while(op!=0);
						System.out.println(" ");
						break;
				}
				break;
				
			case 3:
				System.out.println("It Saddens Us To Know That You Want To Close Your Account.");
				System.out.println("Here's a List Of Existing Accounts...");
				System.out.println();
				for(Account x : list) {
					System.out.println(x);
				}
				System.out.println();
				System.out.print("Set The Number Of The Account To Be Closed: ");
				int acclose = sc.nextInt();
				System.out.println();
				selected = list.stream().filter(x -> x.getNumber() == acclose).findFirst().orElse(null);
					if(selected == null) {
						System.out.println("This Account Does Not Exist! Try Again.");
					}else {
						System.out.println("Are You Sure You Want To Close This Account? ( Y / N )");
						System.out.println(selected);
						response = sc.next().charAt(0);
						if(response == 'y') {
							list.removeIf(x -> x.getNumber() == acclose);
							System.out.println();
							System.out.println("Account Sucesfully Closed. Plase wait until you money is released...");
						}else {
							System.out.println("The Account Has Not Been closed!");
						}
					
					}				
			}		
		}while(number != 0);
		
		System.out.println("Your cash is ready for the withdraw. Thank You for use our services... Hope to see you soon!");
		sc.close();
	}
}