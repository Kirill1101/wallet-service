package io.ylab.walletservice.infrastucuture.factory;

/**
 * Класс для генерации id кошелька.
 */
public class WalletIdFactory {
    private static int currentId = 0;

    /**
     * Метод для получения уникального id кошелька.
     * @return Возвращает уникальный id кошелька.
     */
    public static int getNewWalletId() {
        return currentId++;
    }
}
