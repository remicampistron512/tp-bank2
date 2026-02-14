package bank.model;

import java.math.BigDecimal;

public class CheckingAccount extends Account {
 public CheckingAccount(int id, BigDecimal balance, String type) {
    super(id, balance, type);
  }
}
