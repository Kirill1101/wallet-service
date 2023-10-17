package io.ylab.walletservice.infrastucuture.repository_impl;

import io.ylab.walletservice.infrastucuture.factory.UserIdFactory;
import io.ylab.walletservice.model.Admin;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.repository.UserRepository;
import io.ylab.walletservice.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, реализующий интерфейс UserRepository. Содержит работу с пользователями.
 */
public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public UserRepositoryImpl() {
        users.put("admin", new Admin(UserIdFactory.getNewUserId(), "admin", "admin", new ArrayList<>()));
    }

    @Override
    public User getUserByLogin(String login) {
        return users.get(login);
    }

    @Override
    public void saveUser(User user) {
        users.put(user.getLogin(), user);
    }

    @Override
    public void saveAction(User user, String action) {
        user.getActions().add(action);
    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        for (Map.Entry<String, User> pair : users.entrySet()) {
            if (!pair.getKey().equals("admin")) {
                usersList.add(pair.getValue());
            }
        }
        return usersList;
    }

    @Override
    public List<String> getAllActions(User user) {
        return user.getActions();
    }
}
