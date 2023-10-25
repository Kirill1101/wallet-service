package io.ylab.walletservice.infrastucuture.repository_impl;

import io.ylab.walletservice.infrastucuture.factory.ConnectionFactory;
import io.ylab.walletservice.model.Role;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.model.Wallet;
import io.ylab.walletservice.repository.WalletRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WalletRepositoryImpl implements WalletRepository {

    public WalletRepositoryImpl() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            try (ResultSet rs = statement.executeQuery("SELECT * FROM wallet.wallet")) {
                while (rs.next()) {
                    Wallet wallet = new Wallet(rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3));
                    wallets.put(wallet.getUserLogin(), wallet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final Map<String, Wallet> wallets = new HashMap<>();

    @Override
    public Wallet getWalletByLogin(String login) {
        return wallets.get(login);
    }

    @Override
    public void createWallet(String userLogin) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO wallet.wallet (user_login, balance)" +
                    " VALUES ('%s', '%d')", userLogin, 0));
            try (ResultSet rs = statement.executeQuery(String.format("SELECT id FROM wallet.wallet WHERE user_login='%s'", userLogin))) {
                rs.next();
                Wallet wallet = new Wallet(rs.getInt("id"), userLogin, 0);
                wallets.put(wallet.getUserLogin(), wallet);
                wallets.put(userLogin, wallet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
