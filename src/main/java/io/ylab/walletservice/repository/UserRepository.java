package io.ylab.walletservice.repository;

import io.ylab.walletservice.model.User;

import java.util.List;

/**
 * Интерфейс, сожержит методы, которые необходимы для работы с пользователями.
 */
public interface UserRepository {
    /**
     * Получение пользователя по его логину.
     * @param login Логин.
     * @return Возвращает логин.
     */
    User getUserByLogin(String login);

    /**
     * Сохранение пользователя.
     * @param user Пользователь.
     */
    void saveUser(User user);

    /**
     * Сохранение действия пользователя.
     * @param user Пользователь.
     * @param action Действие.
     */
    void saveAction(User user, String action);

    /**
     * Получение всех действий пользователя.
     * @param user Пользователь.
     * @return Возвращает список вссех действий пользователя.
     */
    List<String> getAllActions(User user);

    /**
     * Получение всех пользователей.
     * @return Возвращает список всех пользователей.
     */
    List<User> getAllUsers();
}
