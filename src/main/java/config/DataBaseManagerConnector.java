package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseManagerConnector {
    private static DataBaseManagerConnector Instance;

    static {
        Instance = new DataBaseManagerConnector();
    }

    public Connection connection;
    private String url;

    public static DataBaseManagerConnector getInstance() {
        return Instance;
    }

    private DataBaseManagerConnector() {
        Properties properties = init();
        String url = String.format("jdbc:postgresql://%s:%s/%s", properties.get("host"), properties.get("port"), properties.get("databaseName"));

        try {
            this.connection = DriverManager.getConnection(url, properties.getProperty("dbUserName"), properties.getProperty("dbPassword"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties init() {
        Properties properties = new PropertiesConfig().loadProperties("application.properties");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Error loading postgres driver", ex);
        }
        return properties;
    }

    public Connection getConnector() {
        return connection;
    }
}
