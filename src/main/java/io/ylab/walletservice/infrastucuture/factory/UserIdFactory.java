package io.ylab.walletservice.infrastucuture.factory;

/**
 * Класс для генерации id пользователя.
 */
public class UserIdFactory {
    private static int currentId = 0;

    /**
     * Метод для получения уникального id пользователя.
     * @return Возвращает целое число - уникальный id пользователя.
     */
    public static int getNewUserId() {
        return currentId++;
    }
}

