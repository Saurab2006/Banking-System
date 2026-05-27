package BankingSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;

    // ── Variables required by the assessment brief
    private Transaction transferObject = new Transaction();
    private StringBuilder sbAllData = new StringBuilder();
    private LinkedList<Account> globalAccounts;
    private String csvFile = "Accounts.csv";

    // ── Panels
    private JPanel contentPane;
    private JPanel accountPanel;
    private JPanel addAccountPanel;
    private JPanel depositPanel;
    private JPanel withdrawPanel;
    private JPanel transferPanel;

    // ── Labels 
    private JLabel titleLabel;
    private JLabel showAllData;
    private JLabel depositAccountLabel;
    private JLabel depositAmountLabel;
    private JLabel withdrawAccountLabel;
    private JLabel withdrawAmountLabel;
    private JLabel fromAccountLabel;
    private JLabel toAccountLabel;
    private JLabel transferAmountLabel;
    private JLabel messageLabel;

    private JLabel addFirstNameLabel;
    private JLabel addLastNameLabel;
    private JLabel addAccountNumLabel;
    private JLabel addBalanceLabel;

    // ── Buttons 
    private JButton showAllButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton addAccountButton;

    // ── Text Fields 
    private JTextField accDeposit;
    private JTextField accWithdraw;
    private JTextField acc1Transfer;
    private JTextField acc2Transfer;
    private JTextField depositInput;
    private JTextField withdrawInput;
    private JTextField transferAmount;

    private JTextField addFirstNameField;
    private JTextField addLastNameField;
    private JTextField addAccountNumField;
    private JTextField addBalanceField;

    // ── Constructor: takes the LinkedList of accounts from Main
    public GUI(LinkedList<Account> accounts) {

        // Set the title of the GUI window
        super("Banking System");

        // Allow absolute positioning of all components
        getContentPane().setLayout(null);

        // Store the accounts passed in from Main into the global variable
        globalAccounts = accounts;

        // Populate sbAllData with all account info in a readable format
        for (Account account : globalAccounts) {
            sbAllData.append("Name: ")
                     .append(account.getFirstName()).append(" ").append(account.getLastName())
                     .append(" | Account: ").append(account.getAccountNum())
                     .append(" | Balance: ").append(account.getBalance())
                     .append("  ");
        }

        // Build the full GUI layout
        initialize();
    }

    // ── Extra constructors kept for convenience
    public GUI() {
        super("Banking System");
        getContentPane().setLayout(null);
        globalAccounts = new LinkedList<Account>();
        initialize();
    }

    public GUI(LinkedList<Account> accounts, String file) {
        super("Banking System");
        getContentPane().setLayout(null);
        globalAccounts = accounts;
        csvFile = file;
        for (Account account : globalAccounts) {
            sbAllData.append("Name: ")
                     .append(account.getFirstName()).append(" ").append(account.getLastName())
                     .append(" | Account: ").append(account.getAccountNum())
                     .append(" | Balance: ").append(account.getBalance())
                     .append("  ");
        }
        initialize();
    }

    //GUI components 
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 760, 830);
        setResizable(true);

        // Outer panel fills the window; inner contentPane stays centered
        JPanel outerPane = new JPanel(null);
        outerPane.setBackground(new Color(20, 30, 48));
        setContentPane(outerPane);

        contentPane = new JPanel();
        contentPane.setBackground(Color.PINK);
        contentPane.setBounds(0, 0, 750, 800);
        contentPane.setLayout(null);
        outerPane.add(contentPane);

        // Re-center contentPane horizontally whenever the window is resized
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                int paneW = getContentPane().getWidth();
                int paneH = getContentPane().getHeight();
                int x = Math.max(0, (paneW - 750) / 2);
                contentPane.setBounds(x, 0, 750, paneH);
            }
        });

        // ── Title 
        titleLabel = new JLabel("Banking System");
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBounds(30, 18, 300, 32);
        contentPane.add(titleLabel);

        // ── Account Information panel
        accountPanel = new JPanel();
        accountPanel.setLayout(null);
        accountPanel.setBackground(new Color(30, 42, 68));
        accountPanel.setBounds(30, 65, 690, 185);
        accountPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255)),
                "Account Information",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(0, 200, 255)));
        contentPane.add(accountPanel);

        showAllButton = new JButton("Show All Accounts");
        showAllButton.setForeground(Color.WHITE);
        showAllButton.setBackground(new Color(0, 123, 255));
        showAllButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        showAllButton.setBounds(20, 35, 165, 35);
        accountPanel.add(showAllButton);

        showAllData = new JLabel("<html>Click Show All Accounts to display account details.</html>");
        showAllData.setFont(new Font("Consolas", Font.PLAIN, 13));
        showAllData.setForeground(Color.WHITE);
        showAllData.setOpaque(true);
        showAllData.setBackground(new Color(20, 30, 48));
        showAllData.setVerticalAlignment(JLabel.TOP);
        showAllData.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255)));
        showAllData.setBounds(205, 35, 460, 125);
        accountPanel.add(showAllData);

        // ── Add Account panel ──────────────────────────────────────────────────
        addAccountPanel = new JPanel();
        addAccountPanel.setLayout(null);
        addAccountPanel.setBackground(new Color(30, 42, 68));
        addAccountPanel.setBounds(30, 268, 690, 145);
        addAccountPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255)),
                "Add Account",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(0, 200, 255)));
        contentPane.add(addAccountPanel);

        addFirstNameLabel = new JLabel("First Name");
        addFirstNameLabel.setForeground(Color.WHITE);
        addFirstNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addFirstNameLabel.setForeground(Color.WHITE);
        addFirstNameLabel.setBounds(20, 35, 90, 25);
        addAccountPanel.add(addFirstNameLabel);

        addFirstNameField = new JTextField();
        addFirstNameField.setBounds(115, 35, 130, 27);
        addFirstNameField.setColumns(10);
        addAccountPanel.add(addFirstNameField);

        addLastNameLabel = new JLabel("Last Name");
        addLastNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addLastNameLabel.setForeground(Color.WHITE);
        addLastNameLabel.setBounds(270, 35, 90, 25);
        addAccountPanel.add(addLastNameLabel);

        addLastNameField = new JTextField();
        addLastNameField.setBounds(365, 35, 130, 27);
        addLastNameField.setColumns(10);
        addAccountPanel.add(addLastNameField);

        addAccountNumLabel = new JLabel("Account No.");
        addAccountNumLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addAccountNumLabel.setForeground(Color.WHITE);
        addAccountNumLabel.setBounds(20, 78, 90, 25);
        addAccountPanel.add(addAccountNumLabel);

        addAccountNumField = new JTextField();
        addAccountNumField.setBounds(115, 78, 130, 27);
        addAccountNumField.setColumns(10);
        addAccountPanel.add(addAccountNumField);

        addBalanceLabel = new JLabel("Balance");
        addBalanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addBalanceLabel.setForeground(Color.WHITE);
        addBalanceLabel.setBounds(270, 78, 90, 25);
        addAccountPanel.add(addBalanceLabel);

        addBalanceField = new JTextField();
        addBalanceField.setBounds(365, 78, 130, 27);
        addBalanceField.setColumns(10);
        addAccountPanel.add(addBalanceField);

        addAccountButton = new JButton("Add Account");
        addAccountButton.setBackground(new Color(0, 123, 255));
        addAccountButton.setForeground(Color.WHITE);
        addAccountButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addAccountButton.setBounds(530, 55, 135, 32);
        addAccountPanel.add(addAccountButton);

        // ── Deposit panel
        depositPanel = new JPanel();
        depositPanel.setLayout(null);
        depositPanel.setBackground(new Color(30, 42, 68));
        depositPanel.setBounds(30, 432, 320, 140);
        depositPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255)),
                "Deposit",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(0, 200, 255)));
        contentPane.add(depositPanel);

        depositAccountLabel = new JLabel("Account number");
        depositAccountLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        depositAccountLabel.setForeground(Color.WHITE);
        depositAccountLabel.setBounds(20, 35, 120, 25);
        depositPanel.add(depositAccountLabel);

        accDeposit = new JTextField();
        accDeposit.setBounds(145, 35, 145, 27);
        accDeposit.setColumns(10);
        depositPanel.add(accDeposit);

        depositAmountLabel = new JLabel("Amount");
        depositAmountLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        depositAmountLabel.setForeground(Color.WHITE);
        depositAmountLabel.setBounds(20, 72, 120, 25);
        depositPanel.add(depositAmountLabel);

        depositInput = new JTextField();
        depositInput.setBounds(145, 72, 145, 27);
        depositInput.setColumns(10);
        depositPanel.add(depositInput);

        depositButton = new JButton("Deposit");
        depositButton.setBackground(new Color(0, 123, 255));
        depositButton.setForeground(Color.WHITE);
        depositButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        depositButton.setBounds(185, 105, 105, 28);
        depositPanel.add(depositButton);

        // ── Withdraw panel 
        withdrawPanel = new JPanel();
        withdrawPanel.setLayout(null);
        withdrawPanel.setBackground(new Color(30, 42, 68));
        withdrawPanel.setBounds(400, 432, 320, 140);
        withdrawPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255)),
                "Withdraw",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(0, 200, 255)));
        contentPane.add(withdrawPanel);

        withdrawAccountLabel = new JLabel("Account number");
        withdrawAccountLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        withdrawAccountLabel.setForeground(Color.WHITE);
        withdrawAccountLabel.setBounds(20, 35, 120, 25);
        withdrawPanel.add(withdrawAccountLabel);

        accWithdraw = new JTextField();
        accWithdraw.setBounds(145, 35, 145, 27);
        accWithdraw.setColumns(10);
        withdrawPanel.add(accWithdraw);

        withdrawAmountLabel = new JLabel("Amount");
        withdrawAmountLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        withdrawAmountLabel.setForeground(Color.WHITE);
        withdrawAmountLabel.setBounds(20, 72, 120, 25);
        withdrawPanel.add(withdrawAmountLabel);

        withdrawInput = new JTextField();
        withdrawInput.setBounds(145, 72, 145, 27);
        withdrawInput.setColumns(10);
        withdrawPanel.add(withdrawInput);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBackground(new Color(220, 53, 69));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        withdrawButton.setBounds(185, 105, 105, 28);
        withdrawPanel.add(withdrawButton);

        // ── Transfer panel 
        transferPanel = new JPanel();
        transferPanel.setLayout(null);
        transferPanel.setBackground(new Color(30, 42, 68));
        transferPanel.setBounds(30, 592, 690, 145);
        transferPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255)),
                "Transfer",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(0, 200, 255)));
        contentPane.add(transferPanel);

        fromAccountLabel = new JLabel("From account");
        fromAccountLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        fromAccountLabel.setForeground(Color.WHITE);
        fromAccountLabel.setBounds(20, 35, 100, 25);
        transferPanel.add(fromAccountLabel);

        acc1Transfer = new JTextField();
        acc1Transfer.setBounds(125, 35, 125, 27);
        acc1Transfer.setColumns(10);
        transferPanel.add(acc1Transfer);

        toAccountLabel = new JLabel("To account");
        toAccountLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        toAccountLabel.setForeground(Color.WHITE);
        toAccountLabel.setBounds(275, 35, 90, 25);
        transferPanel.add(toAccountLabel);

        acc2Transfer = new JTextField();
        acc2Transfer.setBounds(365, 35, 125, 27);
        acc2Transfer.setColumns(10);
        transferPanel.add(acc2Transfer);

        transferAmountLabel = new JLabel("Amount");
        transferAmountLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        transferAmountLabel.setForeground(Color.WHITE);
        transferAmountLabel.setBounds(20, 80, 100, 25);
        transferPanel.add(transferAmountLabel);

        transferAmount = new JTextField();
        transferAmount.setBounds(125, 80, 125, 27);
        transferAmount.setColumns(10);
        transferPanel.add(transferAmount);

        transferButton = new JButton("Transfer");
        transferButton.setBackground(new Color(40, 167, 69));
        transferButton.setForeground(Color.WHITE);
        transferButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        transferButton.setBounds(365, 80, 125, 28);
        transferPanel.add(transferButton);

        // ── Status bar 
        messageLabel = new JLabel("Ready");
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messageLabel.setForeground(Color.BLUE);
        messageLabel.setBounds(30, 760, 690, 25);
        contentPane.add(messageLabel);

        // ── Wire up listeners
        HandlerClass handler = new HandlerClass();
        showAllButton.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);
        addAccountButton.addActionListener(handler);
    }

    // ── Helper: rebuilds account list from CSV 
    private void reloadAccountsFromCSV() {
        LinkedList<Account> refreshedAccounts = new LinkedList<Account>();
        ReadAccounts readAccounts = new ReadAccounts(csvFile);

        LinkedList<String>  firstNames  = readAccounts.getFirstNames();
        LinkedList<String>  lastNames   = readAccounts.getLastNames();
        LinkedList<Integer> accountList = readAccounts.getAccounts();
        LinkedList<Integer> balanceList = readAccounts.getBalance();   

        for (int i = 0; i < firstNames.size(); i++) {
            refreshedAccounts.add(new Account(
                    firstNames.get(i), lastNames.get(i),
                    accountList.get(i), balanceList.get(i)));
        }

        if (!refreshedAccounts.isEmpty()) {
            globalAccounts = refreshedAccounts;
        }
    }

    // ── Helper: builds and displays all account data in the label
    private void showAllAccounts() {
        reloadAccountsFromCSV();
        sbAllData.setLength(0);
        sbAllData.append("<html>");

        for (Account account : globalAccounts) {
            sbAllData.append("Name: ").append(account.getFirstName())
                     .append(" ").append(account.getLastName())
                     .append(" | Account: ").append(account.getAccountNum())
                     .append(" | Balance: ").append(account.getBalance())
                     .append("<br>");
        }

        if (globalAccounts.isEmpty()) {
            sbAllData.append("No account data loaded yet.");
        }

        sbAllData.append("</html>");
        showAllData.setText(sbAllData.toString());
    }

    // ── Helper: finds an account by account number 
    private Account findAccount(int accountNumber) {
        for (Account account : globalAccounts) {
            if (account.getAccountNum() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    // ── Helper: parses integer from a text field 
    private int readInteger(JTextField field) {
        return Integer.parseInt(field.getText().trim());
    }

    // ── Helper: writes all current account data back to the CSV 
    private void saveAllAccountsToCSV() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(csvFile, false))) {
            for (Account account : globalAccounts) {
                bw.write(account.getFirstName() + "," + account.getLastName() + ","
                       + account.getAccountNum() + "," + account.getBalance());
                bw.newLine();
            }
        }
    }

    // ── Helper: adds a new account to CSV and globalAccounts 
    private void addAccountToCSV(String firstName, String lastName,
                                  int accountNum, int balance) throws IOException {
        if (findAccount(accountNum) != null) {
            messageLabel.setText("Account " + accountNum + " already exists.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(csvFile, true))) {
            bw.write(firstName + "," + lastName + ","
                   + accountNum + "," + balance);
            bw.newLine();
        }

        globalAccounts.add(new Account(firstName, lastName, accountNum, balance));

        addFirstNameField.setText("");
        addLastNameField.setText("");
        addAccountNumField.setText("");
        addBalanceField.setText("");

        messageLabel.setText("Account " + accountNum + " added for "
                + firstName + " " + lastName + ".");
        showAllData.setText("<html>Account added. Click Show All Accounts to refresh view.</html>");
    }

    // ── Private inner class: handles all button click events 
    private class HandlerClass implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {

                // Show All Accounts button pressed
                if (e.getSource() == showAllButton) {
                    showAllAccounts();
                    messageLabel.setText("All account details reloaded from CSV and displayed.");

                // Add Account button pressed
                } else if (e.getSource() == addAccountButton) {
                    String firstName = addFirstNameField.getText().trim();
                    String lastName  = addLastNameField.getText().trim();

                    if (firstName.isEmpty() || lastName.isEmpty()) {
                        messageLabel.setText("Please enter both first name and last name.");
                        return;
                    }

                    int accountNum = Integer.parseInt(addAccountNumField.getText().trim());
                    int balance    = Integer.parseInt(addBalanceField.getText().trim());

                    addAccountToCSV(firstName, lastName, accountNum, balance);

                // Deposit button pressed
                } else if (e.getSource() == depositButton) {
                    int accountNumber = readInteger(accDeposit);
                    int amount        = readInteger(depositInput);
                    Account account   = findAccount(accountNumber);

                    if (account != null) {
                        account.deposit(amount);
                        saveAllAccountsToCSV();
                        showAllData.setText("<html>Deposit complete. Click Show All Accounts to reload.</html>");
                        messageLabel.setText("Deposited " + amount + " into account " + accountNumber + ".");
                    } else {
                        messageLabel.setText("Account " + accountNumber + " was not found.");
                    }

                // Withdraw button pressed
                } else if (e.getSource() == withdrawButton) {
                    int accountNumber = readInteger(accWithdraw);
                    int amount        = readInteger(withdrawInput);
                    Account account   = findAccount(accountNumber);

                    if (account != null) {
                        // Check if there is enough balance before withdrawing
                        if (amount > account.getBalance()) {
                            messageLabel.setText("Withdrawal failed. Insufficient balance. Current balance: " + account.getBalance());
                        } else {
                            account.withdraw(amount);
                            saveAllAccountsToCSV();
                            showAllData.setText("<html>Withdrawal complete. Click Show All Accounts to reload.</html>");
                            messageLabel.setText("Withdrew " + amount + " from account " + accountNumber + ".");
                        }
                    } else {
                        messageLabel.setText("Account " + accountNumber + " was not found.");
                    }

                // Transfer button pressed
                } else if (e.getSource() == transferButton) {
                    int sourceAccountNumber      = readInteger(acc1Transfer);
                    int destinationAccountNumber = readInteger(acc2Transfer);
                    int amount                   = readInteger(transferAmount);
                    Account sourceAccount        = findAccount(sourceAccountNumber);
                    Account destinationAccount   = findAccount(destinationAccountNumber);

                    if (sourceAccount != null && destinationAccount != null) {
                        transferObject.transfer(sourceAccount, destinationAccount, amount);
                        saveAllAccountsToCSV();
                        showAllData.setText("<html>Transfer complete. Click Show All Accounts to reload.</html>");
                        messageLabel.setText("Transferred " + amount + " from account "
                                + sourceAccountNumber + " to account " + destinationAccountNumber + ".");
                    } else {
                        messageLabel.setText("One of the transfer accounts was not found.");
                    }
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter account numbers and amounts as whole numbers only.");
            } catch (IOException ex) {
                messageLabel.setText("Error writing to CSV: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setVisible(true);
        });
    }
}