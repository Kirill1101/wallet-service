package io.ylab.walletservice.infrastucuture.factory;

import io.ylab.walletservice.infrastucuture.repository_impl.UserRepositoryImpl;
import io.ylab.walletservice.services.UserService;

/**
 * Класс для генерации сервиса пользователя.
 */
public class UserServiceFactory {
    private static UserService userService;

    /**
     * Метод для получения сервиса пользователя.
     * @return Возвращает экземпляр пользовательского сервиса.
     */
    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService(new UserRepositoryImpl());
        }
        return userService;
    }
}
