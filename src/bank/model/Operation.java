package bank.model;

import java.math.BigDecimal;
import java.security.Timestamp;

public class Operation {
  int id;
  int accountId;
  String type;
  BigDecimal amount;
  Timestamp operationDate;

  public Operation(int id, int accountId, String type, BigDecimal amount, Timestamp operationDate) {
    this.id = id;
    this.accountId = accountId;
    this.type = type;
    this.amount = amount;
    this.operationDate = operationDate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Timestamp getOperationDate() {
    return operationDate;
  }

  public void setOperationDate(Timestamp operationDate) {
    this.operationDate = operationDate;
  }
}
