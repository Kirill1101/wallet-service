package io.ylab.walletservice.infrastucuture.factory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    public static Connection connectionForTest;
    public static Connection getConnection() throws SQLException {
        if (connectionForTest == null) {
            Properties properties = PropertiesFactory.getProperties();
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            return DriverManager.getConnection(url, username, password);
        } else {
            return connectionForTest;
        }
    }
}
