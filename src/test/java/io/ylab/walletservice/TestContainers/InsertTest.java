package io.ylab.walletservice.TestContainers;

import io.ylab.walletservice.infrastucuture.factory.ConnectionFactory;
import io.ylab.walletservice.model.Role;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class InsertTest {

    @Test
    public void insertIntoUserShouldWorkCorrectly() throws Exception {
        PostgresTestContainer.start();
        try (Statement statement = ConnectionFactory.getConnection().createStatement()) {
            statement.execute("INSERT INTO wallet.user (login, password, actions, role)" +
                    "VALUES ('kir1', 'qwerty', '{registration,take money}', 'User')");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM wallet.user WHERE login='kir1'");
            assertEquals("kir1", resultSet.getString(1));
            assertEquals("qwerty", resultSet.getString(2));
            assertEquals("{registration,take money}", resultSet.getString(3));
            assertEquals(Role.User, Role.valueOf(resultSet.getString(4)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertIntoWalletShouldWorkCorrectly() throws Exception {
        PostgresTestContainer.start();
        try (Statement statement = ConnectionFactory.getConnection().createStatement()) {
            statement.execute("INSERT INTO wallet.wallet (user_login, balance) VALUES ('kir2', '0')");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM wallet.wallet WHERE user_login='kir2'");
            assertEquals("kir2", resultSet.getString(1));
            assertEquals(0, resultSet.getInt(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertIntoTransactionShouldWorkCorrectly() throws Exception {
        PostgresTestContainer.start();
        try (Statement statement = ConnectionFactory.getConnection().createStatement()) {
            statement.execute("INSERT INTO wallet.transaction (id, login, date, balance_before, balance_change, balance_after, wallet_id)\n" +
                    "VALUES ('10', 'kir1', '2019-09-01', '10', '5', '15', '1')");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM wallet.transaction WHERE login='kir1'");
            assertEquals(10, resultSet.getInt(1));
            assertEquals("kir3", resultSet.getString(2));
            assertEquals("2019-09-01", resultSet.getString(3));
            assertEquals(10, resultSet.getInt(4));
            assertEquals(5, resultSet.getInt(5));
            assertEquals(15, resultSet.getInt(6));
            assertEquals(0, resultSet.getInt(7));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
