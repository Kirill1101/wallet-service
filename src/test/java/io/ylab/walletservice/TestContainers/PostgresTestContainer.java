package io.ylab.walletservice.TestContainers;

import io.ylab.walletservice.infrastucuture.factory.ConnectionFactory;
import io.ylab.walletservice.infrastucuture.factory.PropertiesFactory;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class PostgresTestContainer{
    private static PostgreSQLContainer<?> postgreSQLContainer;

    private static Connection connection;

    public static void start() {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");

        postgreSQLContainer.start();

        String jdbcUrl = postgreSQLContainer.getJdbcUrl();
        String username = postgreSQLContainer.getUsername();
        String password = postgreSQLContainer.getPassword();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS migration");
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS wallet");

            Properties properties = PropertiesFactory.getProperties();
            String changeLogFile = properties.getProperty("changeLogFile");
            String defaultSchemaName = properties.getProperty("defaultSchemaName");

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            database.setDefaultSchemaName(defaultSchemaName);
            liquibase.Liquibase liquibase = new liquibase.Liquibase(changeLogFile,
                    new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            ConnectionFactory.connectionForTest = connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop(){
        ConnectionFactory.connectionForTest = null;
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        postgreSQLContainer.stop();
    }
}
