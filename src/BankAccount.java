package Java_Projects;
//Achieving hierarchical inheritance

//parent class
class BankAccount {
	String accountNumber;
	String holderName;
	double balance;

	public BankAccount(String accountNumber, String holderName, double balance) {
		this.accountNumber = accountNumber;
		this.holderName = holderName;

		if (balance < 0) {
			this.balance = 0.0;
		} else {
			this.balance = balance;
		}
		System.out.println("Base: Account [" + accountNumber + "] create for " + holderName);
	}

	void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
		} else {
			System.out.println("Invalid deposit amount");
		}
	}

	void printBaseDetails() {
		System.out.println("----------------------------------");
		System.out.println("Account Number: " + accountNumber);
		System.out.println("Account holder name: " + holderName);
		System.out.println("Current Balance: " + balance);
		System.out.println("----------------------------------");
	}
}

// child 1
class SavingAccount extends BankAccount {
	double minimumBalance;

	public SavingAccount(String accountNumber, String holderName, double balance, double minimumBalance) {
		super(accountNumber, holderName, balance);
		if (minimumBalance < 1000) {
			System.out.println("Minimum Balance should be 1000");
		} else {
			this.minimumBalance = minimumBalance;
		}
		System.out.println("Child 1: Saving account configured with Min Balance ₹" + minimumBalance);
	}

	boolean withdraw(double amount) {
		if ((balance - amount) >= minimumBalance && amount > 0) {
			balance -= amount;
			return true;
		} else {
			System.out.println("Withdrawal failed: Minimum balance limit breached.");
			return false;
		}
	}

}

// child 2
class CurrentAccount extends BankAccount {
	double overdraftLimit;

	public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit) {
		super(accountNumber, holderName, balance);
		if (overdraftLimit >= 5000) {
			this.overdraftLimit = overdraftLimit;
		} else {
			System.out.println("Overdraft should be 5000.0");
		}
		System.out.println("Child 2: Current Account configured with Overdraft ₹" + overdraftLimit);
	}

	boolean withdraw(double amount) {
		if (amount > 0 && (balance - amount) >= -overdraftLimit) {
			balance -= amount;
			return true;
		} else {
			System.out.println("Withdrawl failed: Overdraft limit exceeded.");
			return false;
		}
	}
}

public class BankAccountManagement {
	public static void main(String[] args) throws InterruptedException {

		SavingAccount sa = new SavingAccount("SA-101", "Zaid Ajmeri", 5000, 1000);
		sa.withdraw(3000);
		Thread.sleep(2000);

		sa.printBaseDetails();

		CurrentAccount ca = new CurrentAccount("CA-101", "Tech Corp", 2000, 5000);
		ca.withdraw(6000); // Leaves balance at -4000.0 (within overdraft)

		ca.withdraw(2000);// Fails (would exceed overdraft limit of -5000.0)
		Thread.sleep(2000);

		ca.printBaseDetails();

	}
}
