package io.ylab.walletservice.repository;

import io.ylab.walletservice.model.Wallet;

/**
 * Интерфейс, сожержит методы, которые необходимы для работы с кошельком.
 */
public interface WalletRepository {

    /**
     * Получить кошелек пользователя по его логину.
     * @param login Логин.
     * @return Возвращает кошелек пользователя.
     */
    Wallet getWalletByLogin(String login);

    /**
     * Сохранение кошелька.
     * @param wallet Кошелек.
     */
    void saveWallet(Wallet wallet);
}
