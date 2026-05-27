package BankingSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadAccounts {
    private String URL;
    private LinkedList<String> firstNames = new LinkedList<String>();
    private LinkedList<String> lastNames = new LinkedList<String>();
    private LinkedList<Integer> accounts = new LinkedList<Integer>();
    private LinkedList<Integer> balances = new LinkedList<Integer>();

    public ReadAccounts(String URL) {
        this.URL = URL;

        try (BufferedReader br = new BufferedReader(new FileReader(this.URL))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    firstNames.add(values[0].replace("\uFEFF", "").trim());
                    lastNames.add(values[1].trim());
                    accounts.add(Integer.parseInt(values[2].trim()));
                    balances.add(Integer.parseInt(values[3].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read account file: " + this.URL);
        } catch (NumberFormatException e) {
            System.out.println("Account file contains invalid number data.");
        }
    }

    public LinkedList<String> getFirstNames() {
        return firstNames;
    }

    public LinkedList<String> getLastNames() {
        return lastNames;
    }

    public LinkedList<Integer> getAccounts() {
        return accounts;
    }

    public LinkedList<Integer> getBalance() {
        return balances;
    }
}