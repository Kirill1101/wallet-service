package io.ylab.walletservice.repository;

import io.ylab.walletservice.model.Transaction;

import java.util.ArrayList;

public interface TransactionRepository {

    Transaction getTransactionById(int id);

    ArrayList<Transaction> getTransactionsByWalletId(int walletId);

    void saveTransaction(Transaction transaction);

}
