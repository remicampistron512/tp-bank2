package bank.dao;

import bank.config.ConnectionInstantiator;
import bank.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

  public Customer createCustomer(String phone,
      String address,
      String firstName,
      String lastName,
      String email) {

    String sql = """
        INSERT INTO customer (cus_phone, cus_address, cus_first_name, cus_last_name, cus_email)
        VALUES (?, ?, ?, ?, ?)
        """;

    try (Connection cn = ConnectionInstantiator.getInstance().getConnection();
        PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      ps.setString(1, phone);
      ps.setString(2, address);
      ps.setString(3, firstName);
      ps.setString(4, lastName);
      ps.setString(5, email);

      int updated = ps.executeUpdate();
      if (updated != 1) throw new DaoException("Customer insert failed.");

      try (ResultSet keys = ps.getGeneratedKeys()) {
        if (!keys.next()) throw new DaoException("Customer inserted but no generated key returned.");
        int id = keys.getInt(1);

        // Adjust if your Customer constructor differs
        return new Customer(id, phone, address, firstName, lastName, email);
      }

    } catch (SQLException e) {
      throw new DaoException("Failed to create customer.", e);
    }
  }

  public List<Customer> findAll() {
    String sql = """
        SELECT cus_id, cus_first_name, cus_last_name,cus_phone,cus_address,cus_email
        FROM customer
        ORDER BY cus_last_name, cus_first_name
        """;

    List<Customer> customers = new ArrayList<>();

    try (Connection cn = ConnectionInstantiator.getInstance().getConnection();
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        customers.add(new Customer(
            rs.getInt("cus_id"),
            rs.getString("cus_first_name"),
            rs.getString("cus_last_name"),
            rs.getString("cus_email"),
            rs.getString("cus_phone"),
            rs.getString("cus_address")

        ));
      }
      return customers;

    } catch (SQLException e) {
      throw new DaoException("Failed to list customers.", e);
    }
  }

  public Customer findById(int customerId) {
    String sql = """
        SELECT cus_id,cus_phone,cus_address,cus_first_name,cus_last_name,cus_email,cus_created_at
        FROM customer
        WHERE cus_id = ?
        """;

    try (Connection cn = ConnectionInstantiator.getInstance().getConnection();
        PreparedStatement ps = cn.prepareStatement(sql)) {

      ps.setInt(1, customerId);

      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next()) throw new DaoException("Customer not found: id=" + customerId);

        return new Customer(
            rs.getInt("cus_id"),
            rs.getString("cus_first_name"),
            rs.getString("cus_last_name"),
            rs.getString("cus_email"),
            rs.getString("cus_phone"),
            rs.getString("cus_address")

        );
      }

    } catch (SQLException e) {
      throw new DaoException("Failed to find customer.", e);
    }
  }
}
