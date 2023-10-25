package io.ylab.walletservice.infrastucuture.liquibase;

import io.ylab.walletservice.infrastucuture.factory.ConnectionFactory;
import io.ylab.walletservice.infrastucuture.factory.PropertiesFactory;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

public class Liquibase {
    public static void liquibaseStart() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            String createMigrationSchemaQuery = "CREATE SCHEMA IF NOT EXISTS migration";
            String createWalletSchemaQuery = "CREATE SCHEMA IF NOT EXISTS wallet";
            statement.executeUpdate(createMigrationSchemaQuery);
            statement.executeUpdate(createWalletSchemaQuery);

            Properties properties = PropertiesFactory.getProperties();
            String changeLogFile = properties.getProperty("changeLogFile");
            String defaultSchemaName = properties.getProperty("defaultSchemaName");

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            database.setDefaultSchemaName(defaultSchemaName);
            liquibase.Liquibase liquibase = new liquibase.Liquibase(changeLogFile,
                    new ClassLoaderResourceAccessor(), database);
            liquibase.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
