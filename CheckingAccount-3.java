package edu.weber.cs3230.bankingexceptions;

import java.math.BigDecimal;

// Define the InvalidLimitException class
class InvalidLimitException extends Exception {
    public InvalidLimitException(String message) {
        super(message);
    }
}

public class CheckingAccount {
    private BigDecimal balance;
    private BigDecimal withdrawLimit;

    public CheckingAccount() {
        this.balance = BigDecimal.valueOf(0);
        this.withdrawLimit = BigDecimal.valueOf(Integer.MAX_VALUE);
    }

    public CheckingAccount(BigDecimal withdrawLimit) {
        this();
        this.withdrawLimit = withdrawLimit;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    // Modified setLimit to throw InvalidLimitException for negative limits
    public void setLimit(BigDecimal withdrawLimit) throws InvalidLimitException {
        if (withdrawLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidLimitException("Limit is less than zero!");
        }
        this.withdrawLimit = withdrawLimit;
    }

    public boolean withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0 && amount.compareTo(withdrawLimit) <= 0) {
            balance = balance.subtract(amount);
            return true;
        } else {
            System.out.println("No Enough Balance!");
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
