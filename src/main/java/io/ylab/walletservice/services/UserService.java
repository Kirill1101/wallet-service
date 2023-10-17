package io.ylab.walletservice.services;

import io.ylab.walletservice.model.User;
import io.ylab.walletservice.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Сервис обеспечивающий логику работы с пользователями.
 */
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    /**
     * Создание пользователя.
     * @param user Пользователь.
     */
    public void createUser(User user) {
        userRepository.saveUser(user);
    }

    /**
     * Проверяет, существует ли пользователь.
     * @param login Логин.
     * @return Возвращает соответствующий boolean.
     */
    public boolean userIsExists(String login) {
        return userRepository.getUserByLogin(login) != null;
    }

    /**
     * Получение всех действий пользователя.
     * @param user Пользователь.
     * @return Возвращает список вссех действий пользователя.
     */
    public List<String> getAllActions(User user) {
        return userRepository.getAllActions(user);
    }

    /**
     * Получение всех пользователей.
     * @return Возвращает список всех пользователей.
     */
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    /**
     * Сохранение действия пользователя.
     * @param user Пользователь.
     * @param action Действие.
     */
    public void createAction(User user, String action) {
        userRepository.saveAction(user, action);
    }

    /**
     * Получение пользователя по его логину.
     * @param login Логин.
     * @return Возвращает логин.
     */
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}
