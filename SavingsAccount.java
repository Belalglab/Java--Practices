package edu.weber.cs3230.CodingActivities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SavingsAccount {
    public static void main(String[] args) {
        // Create a new SavingsAccount with a sample name, initial balance, and interest rate
        SavingsAccount account = new SavingsAccount("John Doe", new BigDecimal("1000.00"), new BigDecimal("4.5"));

        System.out.println("Initial Balance: $" + account.getBalance());

        // Deposit an amount
        account.deposit(new BigDecimal("500.00"));
        System.out.println("After Deposit: $" + account.getBalance());

        // Attempt to withdraw an amount
        boolean withdrawalSuccessful = account.withdraw(new BigDecimal("200.00"));
        if (withdrawalSuccessful) {
            System.out.println("After Withdrawal: $" + account.getBalance());
        } else {
            System.out.println("Withdrawal failed due to insufficient funds.");
        }

        // Update balance with interest and display the new balance
        account.updateBalanceWithInterest();
        System.out.println("After Monthly Interest: $" + account.getBalance());
    }

    private static int nextId = 1;
    private int id;
    private String name;
    private LocalDate openingDate;
    private BigDecimal balance;
    private BigDecimal interestRate; // represents an annual percentage rate, e.g., 4.5 means 4.5%

    // Constructor that initializes the account with name, balance, and interest rate
    public SavingsAccount(String name, BigDecimal balance, BigDecimal interestRate) {
        this.id = nextId++;
        this.name = name;
        this.balance = balance;
        this.interestRate = interestRate;
        this.openingDate = LocalDate.now(); // Set the opening date to the current date
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    // Setter method for interest rate
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    // Calculates the monthly interest amount
    public BigDecimal calculateMonthlyInterest() {
        BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(1200), 2, BigDecimal.ROUND_HALF_UP);
        return balance.multiply(monthlyInterestRate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    // Updates the balance with the calculated monthly interest
    public void updateBalanceWithInterest() {
        BigDecimal interest = calculateMonthlyInterest();
        balance = balance.add(interest);
    }

    // Deposits a certain amount into the account
    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    // Withdraws a certain amount from the account
    public boolean withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) { // Checks if the balance is sufficient
            balance = balance.subtract(amount);
            return true;
        } else {
            System.out.println("No Enough Balance!"); // Displays error message for insufficient balance
            return false;
        }
    }
}
