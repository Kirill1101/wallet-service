package io.ylab.walletservice.infrastucuture.repository_impl;

import io.ylab.walletservice.infrastucuture.factory.ConnectionFactory;

import io.ylab.walletservice.model.Role;
import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.repository.UserRepository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public UserRepositoryImpl() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            try (ResultSet rs = statement.executeQuery("SELECT * FROM wallet.user")) {
                while (rs.next()) {
                    User user = new User(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            new ArrayList(Arrays.asList((String[])rs.getArray(4).getArray())),
                            Role.valueOf(rs.getString(5)));
                    users.put(user.getLogin(), user);
                }
            }

            if (users.size() == 0) {
                statement.executeUpdate(String.format("INSERT INTO wallet.user (login, password, actions, role)" +
                                " VALUES ('%s', '%s', '%s', '%s')", "admin", "admin", "{}", Role.Admin));
                try (ResultSet rs = statement.executeQuery("SELECT id FROM wallet.user WHERE login='admin'")) {
                    rs.next();
                    User user = new User(rs.getInt("id"), "admin", "admin",
                            new ArrayList<>(), Role.Admin);
                    users.put(user.getLogin(), user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByLogin(String login) {
        return users.get(login);
    }

    @Override
    public void createUser(String login, String password, Role role) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO wallet.user (login, password, actions, role)" +
                        " VALUES ('%s', '%s', '%s', '%s')", login, password, "{}", role));
                try (ResultSet rs = statement.executeQuery(String.format("SELECT id FROM wallet.user WHERE login='%s'", login))) {
                    rs.next();
                    User user = new User(rs.getInt("id"), login, password,
                            new ArrayList<>(), role);
                    users.put(login, user);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAction(User user, String action) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            user.getActions().add(action);
            statement.executeUpdate(String.format("UPDATE wallet.user SET actions = '{%s}' WHERE login = '%s'",
                    String.join(",", user.getActions()), user.getLogin()));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
