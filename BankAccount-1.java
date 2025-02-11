package edu.weber.cs3230.projects.midterm;

import java.math.BigDecimal;

public class BankAccount {

    private static int nextAccountNumber = 1;

    protected int accountNumber;
    protected BigDecimal balance;

    public BankAccount()
    {
        accountNumber = nextAccountNumber;
        nextAccountNumber++;
        balance = BigDecimal.ZERO;
    }

    public BankAccount(BigDecimal initBalance)
    {
        accountNumber = nextAccountNumber;
        nextAccountNumber++;
        balance = initBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if(balance.compareTo(amount) >= 0)
        {
            balance = balance.subtract(amount);
        }
        else {
            System.out.println("Warning: no enough balance, withdraw fails!");
        }
    }

    public String getStatement()
    {
        String accountNumberStr = String.format("%04d", accountNumber);
        String balanceStr = String.format("$%.2f", balance);
        return "Account Number = " + accountNumberStr + ", Balance = " + balanceStr;
    }

    public static int getNextAccountNumber() {
        return nextAccountNumber;
    }

    public static void setNextAccountNumber(int nextAccountNumber) {
        BankAccount.nextAccountNumber = nextAccountNumber;
    }

}