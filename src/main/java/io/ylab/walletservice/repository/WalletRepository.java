package io.ylab.walletservice.repository;

import io.ylab.walletservice.model.Wallet;

public interface WalletRepository {

    Wallet getWalletByLogin(String login);

    void createWallet(String userLogin);
}
