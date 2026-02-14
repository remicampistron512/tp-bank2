package bank.dao;

import bank.config.ConnectionInstantiator;
import bank.factory.AccountFactory;
import bank.model.Account;
import bank.model.CheckingAccount;
import bank.model.Customer;
import bank.model.Operation;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
  public Account createAccount(Customer customer, String accNumber, String type,
      BigDecimal initialBalance, BigDecimal interestRate) {

    String sqlAccount = """
    INSERT INTO account (acc_number, acc_type, acc_owner_cus_id, acc_balance, acc_interest_rate, acc_created_at)
    VALUES (?, ?, ?, ?, ?, ?)
  """;

    Connection cn = null;
    try {
      cn = ConnectionInstantiator.getInstance().getConnection();
      cn.setAutoCommit(false);

      try (PreparedStatement ps = cn.prepareStatement(sqlAccount, PreparedStatement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, accNumber);
        ps.setString(2, type);
        ps.setLong(3, customer.getId());
        ps.setBigDecimal(4, initialBalance);
        ps.setBigDecimal(5, interestRate);
        ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

        int rows = ps.executeUpdate();
        if (rows != 1) {
          throw new DaoException("Insert failed: no row inserted.");
        }

        long newAccountId;
        try (ResultSet rs = ps.getGeneratedKeys()) {
          if (!rs.next()) {
            throw new DaoException("Insert failed: no generated key returned.");
          }
          newAccountId = rs.getLong(1);
        }

        cn.commit();
        return AccountFactory.createAccount(type, (int) newAccountId,accNumber,initialBalance,customer);
      }

    } catch (SQLException e) {
      if (cn != null) {
        try { cn.rollback(); } catch (SQLException ignored) {}
      }
      throw new DaoException("Failed to create account.", e);
    } finally {
      if (cn != null) {
        try { cn.close(); } catch (SQLException ignored) {}
      }
    }
  }


  public List<Account> findAll() {
    // Multi-line SQL (text block) for readability
    String sql = """
        SELECT acc_id,acc_number,acc_type,acc_owner_cus_id,acc_balance,acc_overdraft_limit,
        acc_daily_withdrawal_limit,acc_created_at,acc_interest_rate,acc_created_at
        FROM account
        ORDER BY acc_id
        """;

    // Collection to store mapped accounts objects
    List<Account> accounts = new ArrayList<>();

    // Open connection, prepare statement, execute query; all resources auto-close
    try (Connection cn = ConnectionInstantiator.getInstance().getConnection();
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

      // Iterate through result rows and map each row to a accounts object
      while (rs.next()) {
        int accountId = rs.getInt("acc_id");
        String accountNumber = rs.getString("acc_number");
        String type = rs.getString("acc_type");
        int   customerId = rs.getInt("acc_owner_cus_id");
        BigDecimal balance = rs.getBigDecimal("acc_balance");
        Timestamp createdAt = rs.getTimestamp("acc_created_at");
        BigDecimal interestRate = rs.getBigDecimal("acc_interest_rate");





        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.findById(customerId);

        // Build the domain object from the current row



        accounts.add(AccountFactory.createAccount(type, accountId,accountNumber, balance,customer));
      }

      // Return the full list
      return accounts;

    } catch (SQLException e) {
      // Wrap SQL error into a DAO exception
      throw new DaoException("Failed to list accounts.", e);
    }
  }

  public Account findById(int accountId) {
    // TODO document why this method is empty
    return null;
  }

  public Boolean deposit(Account account, BigDecimal amount) {
    // TODO document why this method is empty
    return null;
  }

  public Boolean transfer(BigDecimal amount, Account sourceAccount, Account targetAccount) {
    return null;
  }
}
