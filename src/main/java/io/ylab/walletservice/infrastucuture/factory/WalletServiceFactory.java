package io.ylab.walletservice.infrastucuture.factory;

import io.ylab.walletservice.infrastucuture.repository_impl.WalletRepositoryImpl;
import io.ylab.walletservice.services.WalletService;

/**
 * Класс для генерации сервиса кошелька.
 */
public class WalletServiceFactory {
    private static WalletService walletService;

    /**
     * Метод для получения сервиса кошелька.
     * @return Возвращает экземпляр серсиса кошелька.
     */
    public static WalletService getWalletService() {
        if (walletService == null) {
            walletService = new WalletService(new WalletRepositoryImpl());
        }
        return walletService;
    }
}
