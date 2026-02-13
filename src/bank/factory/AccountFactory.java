package bank.factory;

import bank.model.*;
import java.math.BigDecimal;

public final class AccountFactory {

  private AccountFactory() {}

  public static Account createAccount(String type, int id, BigDecimal balance) {
    if (type == null) throw new IllegalArgumentException("type is null");

    switch (type.trim().toUpperCase()) {
      case "CHECKING":
        return new CheckingAccount(id, balance, "CHECKING");
      case "SAVINGS":
        return new SavingsAccount(id, balance, "SAVINGS");
      default:
        throw new IllegalArgumentException("Unknown account type: " + type);
    }
  }
}
