package io.ylab.walletservice.services;

import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.repository.TransactionRepository;
import lombok.AllArgsConstructor;

/**
 * Сервис обеспечивающий логику работы с транзакциями.
 */
@AllArgsConstructor
public class TransactionService {
    TransactionRepository transactionRepository;

    /**
     * Получить транзакцию по её id.
     * @param id Id.
     * @return Возвращает соотетствующую транзакцию.
     */
    public Transaction getTransactionById(int id) {
        return transactionRepository.getTransactionById(id);
    }

    /**
     * Создание транзакции.
     * @param transaction Транзакция.
     */
    public void createTransaction(Transaction transaction) {
        transactionRepository.saveTransaction(transaction);
    }

    /**
     * Получить информацию о транзакции в виде строки.
     * @param transaction Транзакция.
     * @return Возвращает строку, которая описывает запрашиваемую транзакцию.
     */
    public String getInfoFromTransactionAsString(Transaction transaction) {
        return transactionRepository.getInfoAboutTransactionAsString(transaction);
    }
}
