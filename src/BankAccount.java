import java.util.ArrayList;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, String accountType, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        addTransaction(new Transaction("Initial Deposit", initialDeposit, balance));
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction(new Transaction("Deposit", amount, balance));
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            addTransaction(new Transaction("Withdrawal", amount, balance));
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public double getBalance() {
        return balance;
    }

    private void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
