package io.ylab.walletservice.infrastucuture.in.state.Wallet;

import io.ylab.walletservice.infrastucuture.factory.TransactionServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.WalletServiceFactory;
import io.ylab.walletservice.infrastucuture.in.state.ConsoleState;
import io.ylab.walletservice.model.Transaction;
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

    public TransactionHistoryState(Wallet wallet) {
        this.wallet = wallet;
        this.walletService = WalletServiceFactory.getWalletService();
        this.transactionService = TransactionServiceFactory.getTransactionService();
        this.userService = UserServiceFactory.getUserService();
        this.nextState = new WalletState(wallet.getUser());
    }

    @Override
    public void process() throws Exception {
        userService.createAction(wallet.getUser(), "Пользователь просмотрел историю транзакций");
        for (Transaction transaction : walletService.getAllTransactionFromUser(wallet)) {
            System.out.println(transactionService.getInfoFromTransactionAsString(transaction));
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
