package edu.weber.cs3230.projects.midterm;

import java.math.BigDecimal;

public class BankingService implements IBankingService {

    private ICustomerRepository customerRepository;
    private IBankAccountRepository bankAccountRepository;

    public BankingService(ICustomerRepository customerRepository, IBankAccountRepository bankAccountRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount openBankAccount(BankAccountType accountType, Customer customer) {
        BankAccount newAccount = null;
        switch (accountType) {
            case CHECKING:
                newAccount = new CheckingAccount() {
                    /**
                     * @param newBalance
                     */
                    @Override
                    public void setBalance(BigDecimal newBalance) {

                    }
                };
                break;
            case SAVINGS:
                newAccount = new SavingsAccount();
                break;
            case INVESTMENT:
                newAccount = new InvestmentAccount();
                break;
        }
        if (newAccount != null) {
            customer.addBankAccount(newAccount);
            bankAccountRepository.addAccount(newAccount);
        }
        return newAccount;
    }

    @Override
    public void closeBankAccount(int accountNumber) {
        try {
            Customer owner = customerRepository.findCustomerByAccountNumber(accountNumber);
            BankAccount accountToClose = bankAccountRepository.findAccountByNumber(accountNumber);
            owner.getBankAccounts().remove(accountToClose);
            bankAccountRepository.removeAccount(accountToClose);
        } catch (NoSuchCustomerException | NoSuchBankAccountException e) {
            e.printStackTrace(); // Consider proper logging or rethrowing as a different exception
        }
    }
}
