package bank.service;
import bank.dao.CustomerDao;
import bank.dao.AccountDao;
import bank.model.Account;
import bank.model.Customer;
import bank.model.Operation;
import java.math.BigDecimal;
import java.util.List;

public class BankingService {
  public  Account createAccount(Customer customer,String accNumber, String type, BigDecimal initialBalance,BigDecimal interestRate){
    AccountDao accountDao = new AccountDao();
    return accountDao.createAccount(customer,accNumber,type,initialBalance,interestRate);
  }

  // Customers
  public Customer createCustomer(String phone, String address, String firstName, String lastName,String email) {
    CustomerDao customerDao = new CustomerDao();
    return customerDao.createCustomer(phone,address,firstName,lastName,email);
  }

  public List<Customer> listCustomers() {
    CustomerDao customerDao = new CustomerDao();
    return customerDao.findAll();
  }

  public List<Account> listAccounts() {
    AccountDao accountDao = new AccountDao();
    return accountDao.findAll();
  }


  public Account getAccount(String accountNumber, boolean includeHistory) {
    throw new UnsupportedOperationException("Not implemented");
  }

  // Operations
  public void deposit(String accountNumber, BigDecimal amount, Long advisorId) {
    throw new UnsupportedOperationException("Not implemented");
  }

  public void withdraw(String accountNumber, BigDecimal amount, Long advisorId) {
    throw new UnsupportedOperationException("Not implemented");
  }

  public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount, Long advisorId) {
    throw new UnsupportedOperationException("Not implemented");
  }

  public List<Operation> getHistory(String accountNumber) {
    throw new UnsupportedOperationException("Not implemented");
  }

  public Account getAccountById(int accountId) {
    AccountDao accountDao = new AccountDao();
    return accountDao.findById(accountId);
  }

  public Boolean deposit(Account account, BigDecimal amount) {
    AccountDao accountDao = new AccountDao();
    return accountDao.deposit(account,amount);
  }

  public Boolean transfer(BigDecimal amount,Account sourceAccount,Account targetAccount){
    AccountDao accountDao = new AccountDao();
    return accountDao.transfer(amount,sourceAccount,targetAccount);
  }


}
