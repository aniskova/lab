package by.gsu.epamlab.util;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private final static String DATASOURCE_NAME = "java:comp/env/jdbc/todoapp";
    private static DataSource dataSource;
    static {
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource)initContext.lookup(DATASOURCE_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    private ConnectionPool() {}
    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
