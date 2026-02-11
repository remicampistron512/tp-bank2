package bank.config;

/* Instantiate a new connection, uses singleton design pattern */
public class ConnectionInstantiator {
 static ConnectionInstantiator instance;
 private Connection connection;
 private String url;
 private String user;
 private String password;

 private ConnectionInstantiator(){

 }
 public static ConnectionInstantiator getInstance(){

 }

 public static Connection getConnection(){

 }

 public static void closeConnection(){

 }

}
