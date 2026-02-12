package bank.model;

import java.math.BigDecimal;

public abstract class Account {
 int id;
 BigDecimal balance;
 String type;

  protected Account(int id, BigDecimal balance, String type) {
    this.id = id;
    this.balance = balance;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getType (){
    return type;
  }
}
