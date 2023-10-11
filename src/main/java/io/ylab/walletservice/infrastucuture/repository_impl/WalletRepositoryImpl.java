package io.ylab.walletservice.infrastucuture.repository_impl;

import io.ylab.walletservice.model.User;
import io.ylab.walletservice.model.Wallet;
import io.ylab.walletservice.repository.WalletRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, реализующий интерфейс WalletRepository. Содержит работу с кошельком.
 */
public class WalletRepositoryImpl implements WalletRepository {

    private final Map<String, Wallet> wallets = new HashMap<>();

    @Override
    public Wallet getWalletByLogin(String login) {
        return wallets.get(login);
    }

    @Override
    public void saveWallet(Wallet wallet) {
        wallets.put(wallet.getUser().getLogin(), wallet);
    }
}
