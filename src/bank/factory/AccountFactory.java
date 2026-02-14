package bank.factory;

import bank.model.*;
import java.math.BigDecimal;
import java.util.Objects;

public final class AccountFactory {

  private AccountFactory() {}

  public static Account createAccount(String type, int id, String accountNumber, BigDecimal balance, Customer owner) {
    if (type == null || type.isBlank()) {
      throw new IllegalArgumentException("type is null/blank");
    }
    Objects.requireNonNull(balance, "balance is null");
    Objects.requireNonNull(owner, "owner is null");

    switch (type.trim().toUpperCase()) {
      case "CHECKING":
        return new CheckingAccount(id, accountNumber, balance, owner);
      case "SAVINGS":
        return new SavingsAccount(id, accountNumber, balance, owner);
      default:
        throw new IllegalArgumentException("Unknown account type: " + type);
    }
  }
}
