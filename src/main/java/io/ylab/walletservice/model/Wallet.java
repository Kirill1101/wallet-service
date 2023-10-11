package io.ylab.walletservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Кошелек.
 */
@Getter
@AllArgsConstructor
public class Wallet {
    private final int id;
    private final User user;
    @Setter
    private int balance;
    private ArrayList<Transaction> transactions;
}
