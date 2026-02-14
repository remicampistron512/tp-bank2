  package bank.model;

  import java.math.BigDecimal;
  import java.util.Objects;

  public abstract class Account {

    private final int id;
    private final String accountNumber; // e.g. "FR-1234-5678"
    private BigDecimal balance;
    private final String type;
    private final Customer owner;

    protected Account(int id, String accountNumber, BigDecimal balance, String type, Customer owner) {
      if (accountNumber == null || accountNumber.isBlank()) {
        throw new IllegalArgumentException("accountNumber is required");
      }
      this.id = id;
      this.accountNumber = accountNumber;
      this.balance = Objects.requireNonNull(balance, "balance is required");
      this.type = Objects.requireNonNull(type, "type is required");
      this.owner = Objects.requireNonNull(owner, "owner is required");
    }

    public int getId() {
      return id;
    }

    public String getAccountNumber() {
      return accountNumber;
    }

    public BigDecimal getBalance() {
      return balance;
    }

    protected void setBalance(BigDecimal balance) {
      this.balance = Objects.requireNonNull(balance, "balance is required");
    }

    public String getType() {
      return type;
    }

    public Customer getAccountOwner() {
      return owner;
    }
  }
