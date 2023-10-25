package io.ylab.walletservice.repository;

import io.ylab.walletservice.model.Role;
import io.ylab.walletservice.model.User;

import java.util.List;

public interface UserRepository {
    User getUserByLogin(String login);

    void createUser(String login, String password, Role role);

    void saveAction(User user, String action);

    List<String> getAllActions(User user);

    List<User> getAllUsers();
}
