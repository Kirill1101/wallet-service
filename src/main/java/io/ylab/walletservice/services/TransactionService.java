package io.ylab.walletservice.services;

import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.repository.TransactionRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;

    public Transaction getTransactionById(int id) {
        return transactionRepository.getTransactionById(id);
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.saveTransaction(transaction);
    }

    public ArrayList<Transaction> getTransactionsByWalletId(int walletId) {
        return transactionRepository.getTransactionsByWalletId(walletId);
    }
}
