import java.util.ArrayList;

public class Bank {
    private ArrayList<BankAccount> accounts;
    private int nextAccountNumber;

    public Bank() {
        accounts = new ArrayList<>();
        nextAccountNumber = 1000;
    }

    public BankAccount createAccount(String accountHolderName, String accountType, double initialDeposit) {
        String accountNumber = String.valueOf(nextAccountNumber++);
        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName, accountType, initialDeposit);
        accounts.add(newAccount);
        return newAccount;
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void deposit(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public double getBalance(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        return 0.0;
    }

    public ArrayList<Transaction> getTransactionHistory(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            return account.getTransactionHistory();
        }
        return null;
    }
}
