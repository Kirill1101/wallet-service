package io.ylab.walletservice.services;

import io.ylab.walletservice.infrastucuture.factory.TransactionServiceFactory;
import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.model.Wallet;
import io.ylab.walletservice.repository.WalletRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class WalletService {
    WalletRepository walletRepository;

    public void createWallet(String userLogin) {
        walletRepository.createWallet(userLogin);
    }

    public Wallet getWalletByLogin(String login) {
        return walletRepository.getWalletByLogin(login);
    }

    public void addMoneyOnBalance(Wallet wallet, int money) {
        wallet.setBalance(wallet.getBalance() + money);
    }

    public boolean takeMoneyFromBalance(Wallet wallet, int money) {
        if (money > wallet.getBalance()) {
            return false;
        } else {
            wallet.setBalance(wallet.getBalance() - money);
            return true;
        }
    }

    public ArrayList<Transaction> getTransactionsByWalletId(int walletId) {
        return TransactionServiceFactory.getTransactionService().getTransactionsByWalletId(walletId);
    }
}
