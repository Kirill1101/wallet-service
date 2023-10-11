package io.ylab.walletservice.repository;

import io.ylab.walletservice.model.Transaction;

/**
 * Интерфейс, сожержит методы, которые необходимы для работы с транзакциями.
 */
public interface TransactionRepository {
    /**
     * Метод, необходимый для получения информации о транзакции в виде строки.
     * @param transaction Транзакция.
     * @return Возвращает информацию о транзакциях в качестве строки.
     */
    String getInfoAboutTransactionAsString(Transaction transaction);

    /**
     * Метод для получения транзакции по id.
     * @param id Id транзакции, которую необходимо найти.
     * @return Возвращает транзакцию.
     */
    Transaction getTransactionById(int id);

    /**
     * Метод, сохранаящий транзакцию.
     * @param transaction Транзакция.
     */
    void saveTransaction(Transaction transaction);
}
