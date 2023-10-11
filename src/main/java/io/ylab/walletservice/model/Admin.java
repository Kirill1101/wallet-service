package io.ylab.walletservice.model;

import java.util.List;

/**
 * Администратор.
 */
public class Admin extends User {
    public Admin(int id, String login, String password, List<String> actions) {
        super(id, login, password, actions);
    }
}
