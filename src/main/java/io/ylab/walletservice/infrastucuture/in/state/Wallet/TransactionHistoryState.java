package io.ylab.walletservice.infrastucuture.in.state.Wallet;

import io.ylab.walletservice.infrastucuture.factory.TransactionServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.WalletServiceFactory;
import io.ylab.walletservice.infrastucuture.in.state.ConsoleState;
import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.model.Wallet;
import io.ylab.walletservice.services.TransactionService;
import io.ylab.walletservice.services.UserService;
import io.ylab.walletservice.services.WalletService;

/**
 * Класс,к который реализует консольный вывод всех транзакций, совершенных пользователем.
 */
public class TransactionHistoryState implements ConsoleState {

    private ConsoleState nextState;
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final UserService userService;
    private final Wallet wallet;
    private final User user;

    public TransactionHistoryState(Wallet wallet) {
        this.wallet = wallet;
        this.walletService = WalletServiceFactory.getWalletService();
        this.transactionService = TransactionServiceFactory.getTransactionService();
        this.userService = UserServiceFactory.getUserService();
        this.user = userService.getUserByLogin(wallet.getUserLogin());
        this.nextState = new WalletState(user);
    }

    @Override
    public void process() throws Exception {
        userService.createAction(user, "Пользователь просмотрел историю транзакций");
        for (Transaction transaction : walletService.getTransactionsByWalletId(wallet.getId())) {
            System.out.println(transaction);
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
