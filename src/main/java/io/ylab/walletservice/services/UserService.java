package io.ylab.walletservice.services;

import io.ylab.walletservice.model.Role;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public void createUser(String login, String password, Role role) {
        userRepository.createUser(login, password, role);
    }

    public boolean userIsExists(String login) {
        return userRepository.getUserByLogin(login) != null;
    }

    public List<String> getAllActions(User user) {
        return userRepository.getAllActions(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void createAction(User user, String action) {
        userRepository.saveAction(user, action);
    }

    public void createAction(String login, String action) {
        userRepository.saveAction(getUserByLogin(login), action);
    }

    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}
