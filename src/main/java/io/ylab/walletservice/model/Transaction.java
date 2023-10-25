package io.ylab.walletservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;


@Getter
@AllArgsConstructor
public class Transaction {
    private final int id;
    private final String login;
    private final Date date;
    private final int balanceBefore;
    private final int balanceChange;
    private final int balanceAfter;
    private final int walletId;

    @Override
    public String toString() {
        return "ID транзакции: " + this.getId() +
                "; ID кошелька: " + this.getWalletId() +
                "; Пользователь: " + this.getLogin() +
                "; Время: " + this.getDate().toString() +
                "; До: " + this.getBalanceBefore() +
                "; Изменение: " + this.getBalanceChange() +
                "; После: " + this.getBalanceAfter();
    }
}
