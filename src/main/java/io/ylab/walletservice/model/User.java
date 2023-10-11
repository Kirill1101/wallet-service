package io.ylab.walletservice.model;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Пользователь.
 */
@Getter
@AllArgsConstructor
public class User {
    private final int id;
    private final String login;
    private String password;
    private List<String> actions;
}
