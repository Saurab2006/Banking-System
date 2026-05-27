package BankingSystem;

public class Account extends Customer {
    private int balance;
    private int accountNumber;

    public Account(String FName, String LName, int accountNumber, int balance) {
        setFirstName(FName);
        setLastname(LName);	
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int getAccountNum() {
        return accountNumber;
    }

    public void deposit(int amount) {
        balance = balance + amount;
    }

    public void withdraw(int amount) {
        // Check if there is enough balance before withdrawing
        if (amount > balance) {
            System.out.println("Withdrawal failed. Insufficient balance. Current balance: " + balance);
        } else {
            balance = balance - amount;
        }
    }
}