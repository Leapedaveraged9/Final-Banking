import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SimpleBankingApp extends Application {
    private Bank bank;

    @Override
    public void start(Stage primaryStage) {
        bank = new Bank();

        primaryStage.setTitle("Simple Banking System");

        // Layouts
        GridPane createAccountPane = new GridPane();
        createAccountPane.setPadding(new Insets(10));
        createAccountPane.setHgap(10);
        createAccountPane.setVgap(10);

        GridPane transactionPane = new GridPane();
        transactionPane.setPadding(new Insets(10));
        transactionPane.setHgap(10);
        transactionPane.setVgap(10);

        VBox root = new VBox(10, createAccountPane, transactionPane);
        root.setPadding(new Insets(10));

        // Create Account Section
        Label lblAccountHolderName = new Label("Account Holder Name:");
        TextField txtAccountHolderName = new TextField();
        Label lblAccountType = new Label("Account Type:");
        TextField txtAccountType = new TextField();
        Label lblInitialDeposit = new Label("Initial Deposit:");
        TextField txtInitialDeposit = new TextField();
        Button btnCreateAccount = new Button("Create Account");

        createAccountPane.add(lblAccountHolderName, 0, 0);
        createAccountPane.add(txtAccountHolderName, 1, 0);
        createAccountPane.add(lblAccountType, 0, 1);
        createAccountPane.add(txtAccountType, 1, 1);
        createAccountPane.add(lblInitialDeposit, 0, 2);
        createAccountPane.add(txtInitialDeposit, 1, 2);
        createAccountPane.add(btnCreateAccount, 1, 3);

        // Transaction Section
        Label lblAccountNumber = new Label("Account Number:");
        TextField txtAccountNumber = new TextField();
        Label lblAmount = new Label("Amount:");
        TextField txtAmount = new TextField();
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");
        Button btnGetBalance = new Button("Get Balance");
        Button btnGetTransactionHistory = new Button("Transaction History");

        transactionPane.add(lblAccountNumber, 0, 0);
        transactionPane.add(txtAccountNumber, 1, 0);
        transactionPane.add(lblAmount, 0, 1);
        transactionPane.add(txtAmount, 1, 1);
        transactionPane.add(btnDeposit, 0, 2);
        transactionPane.add(btnWithdraw, 1, 2);
        transactionPane.add(btnGetBalance, 0, 3);
        transactionPane.add(btnGetTransactionHistory, 1, 3);

        // Event Handling
        btnCreateAccount.setOnAction(e -> {
            String holderName = txtAccountHolderName.getText();
            String accountType = txtAccountType.getText();
            double initialDeposit = Double.parseDouble(txtInitialDeposit.getText());
            BankAccount newAccount = bank.createAccount(holderName, accountType, initialDeposit);
            showAlert("Account Created", "Account Number: " + newAccount.getAccountNumber());
        });

        btnDeposit.setOnAction(e -> {
            String accountNumber = txtAccountNumber.getText();
            double amount = Double.parseDouble(txtAmount.getText());
            bank.deposit(accountNumber, amount);
            showAlert("Deposit", "Amount Deposited: " + amount);
        });

        btnWithdraw.setOnAction(e -> {
            String accountNumber = txtAccountNumber.getText();
            double amount = Double.parseDouble(txtAmount.getText());
            bank.withdraw(accountNumber, amount);
            showAlert("Withdrawal", "Amount Withdrawn: " + amount);
        });

        btnGetBalance.setOnAction(e -> {
            String accountNumber = txtAccountNumber.getText();
            double balance = bank.getBalance(accountNumber);
            showAlert("Balance", "Current Balance: " + balance);
        });

        btnGetTransactionHistory.setOnAction(e -> {
            String accountNumber = txtAccountNumber.getText();
            ArrayList<Transaction> transactions = bank.getTransactionHistory(accountNumber);
            if (transactions != null) {
                StringBuilder history = new StringBuilder();
                for (Transaction t : transactions) {
                    history.append(t.getDate()).append(" - ").append(t.getType()).append(": ").append(t.getAmount()).append(" - Balance: ").append(t.getBalanceAfterTransaction()).append("\n");
                }
                showAlert("Transaction History", history.toString());
            } else {
                showAlert("Transaction History", "No transactions found.");
            }
        });

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
