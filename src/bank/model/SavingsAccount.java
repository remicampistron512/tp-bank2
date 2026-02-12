package bank.model;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

  public SavingsAccount(int id, BigDecimal balance, String type) {
    super(id, balance, type);
  }
}
