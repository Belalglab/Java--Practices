package edu.weber.cs3230.bankingexceptions;

import java.math.BigDecimal;

public class CheckingAccount {
    private BigDecimal balance;
    private BigDecimal withdrawLimit;

    public CheckingAccount() {
        this.balance = BigDecimal.valueOf(0);
        this.withdrawLimit = BigDecimal.valueOf(Integer.MAX_VALUE);
    }

    public CheckingAccount(BigDecimal withdrawLimit) {
        this();
        if(withdrawLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Withdraw limit cannot be negative at initialization");
        }
        this.withdrawLimit = withdrawLimit;
    }

    public void deposit(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        balance = balance.add(amount);
    }

    public void setLimit(BigDecimal withdrawLimit) throws InvalidLimitException {
        if(withdrawLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidLimitException("Limit is less than zero!");
        }
        this.withdrawLimit = withdrawLimit;
    }

    public boolean withdraw(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative");
        }
        if (balance.compareTo(amount) >= 0 && amount.compareTo(withdrawLimit) <= 0) {
            balance = balance.subtract(amount);
            return true;
        } else {
            System.out.println("No Enough Balance or limit exceeded!");
            return false;
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getWithdrawLimit() {
        return withdrawLimit;
    }
}
