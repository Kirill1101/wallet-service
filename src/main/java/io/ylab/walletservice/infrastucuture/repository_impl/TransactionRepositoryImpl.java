package io.ylab.walletservice.infrastucuture.repository_impl;

import io.ylab.walletservice.infrastucuture.factory.WalletServiceFactory;
import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.repository.TransactionRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, реализующий интерфейс TransactionRepository. Содержит работу с транзакциями.
 */
public class TransactionRepositoryImpl implements TransactionRepository {

    private final Map<Integer, Transaction> transactions = new HashMap<>();
    @Override
    public String getInfoAboutTransactionAsString(Transaction transaction) {
        return "ID транзакции: " + transaction.getId() +
                "; Пользователь: " + transaction.getLogin() +
                "; Время: " + transaction.getDate().toString() +
                "; До: " + transaction.getBalanceBefore() +
                "; Изменение: " + transaction.getBalanceChange() +
                "; После: " + transaction.getBalanceAfter();
    }

    @Override
    public Transaction getTransactionById(int id) {
       return transactions.get(id);
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
        WalletServiceFactory.getWalletService()
                .getWalletByLogin(transaction.getLogin())
                .getTransactions().add(transaction);
    }
}
