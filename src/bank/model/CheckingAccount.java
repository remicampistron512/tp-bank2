package bank.model;

import java.math.BigDecimal;

public class CheckingAccount extends Account {

  public CheckingAccount(int id, String accountNumber, BigDecimal balance, Customer owner) {
    super(id, accountNumber, balance, "CHECKING", owner);
  }
}
