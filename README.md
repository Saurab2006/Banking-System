This is a Java-based Banking System built as part of a project. The system allows users to create bank accounts, deposit money, withdraw money and transfer money between accounts. The system is split into three parts — a basic banking system, CSV file reading and a graphical user interface.

How to Run the Project:
Open Eclipse IDE
Create a new Java project called BankingSystem
Add all the provided .java files into the BankingSystem package
Place the Accounts.csv file in the root folder of the project (same level as src)
Run the Main.java file to start the program
The GUI window will open automatically


Files Included
FileDescriptionCustomer.java - Stores customer first name and last name
Account.java - Extends Customer, handles balance and account number
Transaction.java - Handles money transfer between two accounts
ReadAccounts.java - Reads account data from the CSV file
GUI.java - Graphical user interface built using Java Swing
Main.java - Entry point — loads accounts and launches the GUI
Accounts.csv - Contains account data (first name, last name, account number, balance)
