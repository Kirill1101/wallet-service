package io.ylab.walletservice.infrastucuture.factory;

import io.ylab.walletservice.infrastucuture.repository_impl.TransactionRepositoryImpl;
import io.ylab.walletservice.infrastucuture.repository_impl.WalletRepositoryImpl;
import io.ylab.walletservice.services.TransactionService;
import io.ylab.walletservice.services.WalletService;

/**
 * Класс, необходимый для генерации сервиса транзакций.
 */
public class TransactionServiceFactory {
    private static TransactionService transactionService;

    /**
     * Метод для получения сервиса транзакций.
     * @return Возвращает экземпляр сервиса транзакций.
     */
    public static TransactionService getTransactionService() {
        if (transactionService == null) {
            transactionService = new TransactionService(new TransactionRepositoryImpl());
        }
        return transactionService;
    }
}
