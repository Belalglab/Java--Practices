package edu.weber.cs3230.projects.midterm;

import java.util.List;

public abstract class BankAccountRepository implements IBankAccountRepository {

    private List<BankAccount> bankAccounts;

    public BankAccountRepository(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public BankAccount findByAccountNumber(int accountNumber) {
        for(BankAccount account : bankAccounts)
        {
            if(account.getAccountNumber() == accountNumber)
            {
                return account;
            }
        }

        try {
            throw new NoSuchBankAccountException();
        } catch (NoSuchBankAccountException e) {
            throw new RuntimeException(e);
        }

    }




}
