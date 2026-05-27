package BankingSystem;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
//      Account account1 = new Account("Jeffrey", "Ting", 1, 2000);
//      Account account2 = new Account("Hiran", "pattel", 2, 1000);
//      
//      System.out.println("Jeffrey's balance:" + account1.getBalance());
//      System.out.println("Hiran's balance:" + account2.getBalance());
//      
//      account1.deposit(250);
//      System.out.println("Jeffrey's balance:" + account1.getBalance());
//      
//      account2.withdraw(500);
//      System.out.println("Hiran's balance:" + account2.getBalance());
//      
//      Transaction t = new Transaction();
//      t.transfer(account1, account2, 250);
//      
//      System.out.println("Jeffrey's balance:" + account1.getBalance());
//      System.out.println("Hiran's balance:" + account2.getBalance());

        String file = "Accounts.csv";

        LinkedList<Account> accounts = new LinkedList<Account>();
        ReadAccounts readAccounts = new ReadAccounts(file);

        LinkedList<String> firstNames = readAccounts.getFirstNames();
        LinkedList<String> lastNames = readAccounts.getLastNames();
        LinkedList<Integer> accountList = readAccounts.getAccounts();
        LinkedList<Integer> balanceList = readAccounts.getBalance();

        for (int i = 0; i < firstNames.size(); i++) {
            accounts.add(new Account(firstNames.get(i), lastNames.get(i),
                    accountList.get(i), balanceList.get(i)));
        }

        Account account1 = accounts.get(0);
        Account account2 = accounts.get(1);

        System.out.println("Original account balances");
        printAllAccounts(accounts);

        account1.deposit(250);
        account2.withdraw(500);

        System.out.println("\nAfter deposit and withdraw");
        printAllAccounts(accounts);

        Transaction t = new Transaction();
        t.transfer(account1, account2, 250);

        System.out.println("\nAfter transfer");
        printAllAccounts(accounts);

        GUI gui = new GUI(accounts);
        gui.setVisible(true);
    }

    private static void printAllAccounts(LinkedList<Account> accounts) {
        for (Account account : accounts) {
            printAccount(account);
        }
    }

    private static void printAccount(Account account) {
        System.out.println(account.getFirstName() + " "
                + account.getLastName()
                + " | Account " + account.getAccountNum()
                + " | Balance " + account.getBalance());
    }
}
