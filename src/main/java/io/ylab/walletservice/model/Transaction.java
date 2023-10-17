package io.ylab.walletservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

/**
 * Транзакция.
 */
@Getter
@AllArgsConstructor
public class Transaction {
    private final int id;
    private final String login;
    private final Date date;
    private final int balanceBefore;
    private final int balanceChange;
    private final int balanceAfter;
}
