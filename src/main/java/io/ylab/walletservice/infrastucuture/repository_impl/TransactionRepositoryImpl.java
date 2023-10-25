package io.ylab.walletservice.infrastucuture.repository_impl;

import io.ylab.walletservice.infrastucuture.factory.ConnectionFactory;
import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.WalletServiceFactory;
import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.repository.TransactionRepository;
import org.postgresql.jdbc.PgConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final Map<Integer, Transaction> transactions = new LinkedHashMap<>();

    public TransactionRepositoryImpl() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM wallet.transaction");
            while (rs.next()) {
                Transaction transaction = new Transaction(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7));
                transactions.put(rs.getInt(1), transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction getTransactionById(int id) {
        return transactions.get(id);
    }

    @Override
    public ArrayList<Transaction> getTransactionsByWalletId(int walletId) {
        ArrayList<Transaction> walletTransactions = new ArrayList<>();
        for (Map.Entry<Integer, Transaction> pair : transactions.entrySet()) {
            if (pair.getValue().getWalletId() == walletId) {
                walletTransactions.add(pair.getValue());
            }
        }
        return walletTransactions;
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        try (Connection connection =  ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO wallet.transaction VALUES ('%d', '%s', '%s', '%d', '%d', '%d', '%d')",
                    transaction.getId(), transaction.getLogin(), transaction.getDate(), transaction.getBalanceBefore(),
                    transaction.getBalanceChange(), transaction.getBalanceAfter(), transaction.getWalletId()));
            statement.executeUpdate(String.format("UPDATE wallet.wallet SET balance = %d WHERE user_login = '%s'",
                    transaction.getBalanceAfter(), transaction.getLogin()));
            transactions.put(transaction.getId(), transaction);

            UserServiceFactory.getUserService().createAction(transaction.getLogin(), transaction.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
