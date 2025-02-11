package edu.weber.cs3230.projects.midterm;

import java.math.BigDecimal;

public class TransferService implements ITransfer{

    private IBankAccountRepository bankAccountRepository;

    public TransferService(IBankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public void transfer(int from, int to, BigDecimal amount) throws NoSuchBankAccountException {
        // TODO: transfer a certain amount of money from the bank account with the account number "from"
        //  to a bank account with the account number "to"
        BankAccount fromAccount = bankAccountRepository.getAccountById(from);
        BankAccount toAccount = bankAccountRepository.getAccountById(to);

        if (fromAccount == null || toAccount == null) {
            throw new NoSuchBankAccountException();
        }

        // Assuming BankAccount has a method to check balance
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds for the transfer.");
        }

        // Perform the transfer
        // Assuming withdraw and deposit methods throw exceptions for any illegal operations
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        // Assuming BankAccountRepository has methods to update accounts
        bankAccountRepository.updateAccount(fromAccount);
        bankAccountRepository.updateAccount(toAccount);
    }
}