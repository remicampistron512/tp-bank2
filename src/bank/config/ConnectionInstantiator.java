package bank.config;

import bank.dao.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionInstantiator {

  private static final String PROPS_FILE = "db.properties";
  private final Properties props;

  private static final ConnectionInstantiator INSTANCE = new ConnectionInstantiator();

  private ConnectionInstantiator() {
    this.props = loadProps();
  }

  public static ConnectionInstantiator getInstance() {
    return INSTANCE;
  }

  private static Properties loadProps() {
    Properties p = new Properties();
    try (InputStream in = ConnectionInstantiator.class.getClassLoader().getResourceAsStream(PROPS_FILE)) {
      if (in == null) {
        throw new DaoException("Cannot find " + PROPS_FILE + " on classpath (put it in resources/).");
      }
      p.load(in);
      return p;
    } catch (IOException e) {
      throw new DaoException("Cannot load " + PROPS_FILE + ".", e);
    }
  }

  public Connection getConnection() {
    try {
      return DriverManager.getConnection(
          props.getProperty("db.url"),
          props.getProperty("db.user"),
          props.getProperty("db.password")
      );
    } catch (SQLException e) {
      throw new DaoException("Cannot connect to MySQL. Check db.properties.", e);
    }
  }
}
