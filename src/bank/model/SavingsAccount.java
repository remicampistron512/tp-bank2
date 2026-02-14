package bank.model;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
  public SavingsAccount(int id, String accountNumber, BigDecimal balance, Customer owner) {
    super(id, accountNumber, balance, "SAVINGS", owner);
  }
}
