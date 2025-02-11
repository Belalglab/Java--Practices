package edu.weber.cs3230.projects.midterm;

public class BankingService implements IBankingService {

    private ICustomerRepository customerRepository;
    private IBankAccountRepository bankAccountRepository;

    public BankingService(ICustomerRepository customerRepository, IBankAccountRepository bankAccountRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount openBankAccount(BankAccountType accountType, Customer customer) {
        // Create a new account based on the type specified
        BankAccount newAccount;
        switch (accountType) {
            case CHECKING:
                newAccount = new CheckingAccount();
                break;
            case SAVINGS:
                newAccount = new SavingsAccount();
                break;
            case INVESTMENT:
                newAccount = new InvestmentAccount();
                break;
            default:
                throw new IllegalArgumentException("Invalid account type");
        }

        // Add the new account to the customer's list of bank accounts
        customer.getBankAccounts().add(newAccount);
        // Persist the new account using the bank account repository
        bankAccountRepository.addAccount(newAccount);

        return newAccount;
    }

    @Override
    public void closeBankAccount(int accountNumber) {
        try {
            // Find the owning customer and the account to be removed
            Customer owningCustomer = customerRepository.findCustomerByAccountNumber(accountNumber);
            BankAccount accountToRemove = bankAccountRepository.findAccountByNumber(accountNumber);

            // Remove the account from the customer's list and the repository
            owningCustomer.getBankAccounts().remove(accountToRemove);
            bankAccountRepository.removeAccount(accountToRemove);
        } catch (NoSuchCustomerException | NoSuchBankAccountException e) {
            // Log the exception or handle it as necessary
            System.err.println("Error closing bank account: " + e.getMessage());
        }
    }
}
