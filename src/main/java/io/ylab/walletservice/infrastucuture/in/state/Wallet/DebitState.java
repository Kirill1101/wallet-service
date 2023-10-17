package io.ylab.walletservice.infrastucuture.in.state.Wallet;

import io.ylab.walletservice.infrastucuture.factory.TransactionServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.WalletServiceFactory;
import io.ylab.walletservice.infrastucuture.in.ConsoleManager;
import io.ylab.walletservice.infrastucuture.in.InputValidate;
import io.ylab.walletservice.infrastucuture.in.state.ConsoleState;
import io.ylab.walletservice.infrastucuture.in.state.StartState;
import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.model.Wallet;
import io.ylab.walletservice.services.TransactionService;
import io.ylab.walletservice.services.UserService;
import io.ylab.walletservice.services.WalletService;

import java.util.Date;

/**
 * Класс, который реализует снятие денег со счета пользователя с помощью консоли.
 */
public class DebitState implements ConsoleState {

    private ConsoleState nextState;
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final UserService userService;
    private final Wallet wallet;

    public DebitState(Wallet wallet) {
        this.wallet = wallet;
        this.walletService = WalletServiceFactory.getWalletService();
        this.transactionService = TransactionServiceFactory.getTransactionService();
        this.userService = UserServiceFactory.getUserService();
        nextState = new WalletState(wallet.getUser());
    }

    @Override
    public void process() throws Exception {
        ConsoleManager.separator();
        System.out.println("Введите сумму, которую хотите снять с баланса");
        String sum, id;
        do {
            sum = ConsoleManager.readLine();
            if (sum.equals("exit")) {
                nextState = new StartState();
                userService.createAction(wallet.getUser(), "Пользователь вышел из аккаунта");
                return;
            }
            if (sum.equals("back")) {
                return;
            }
        } while (InputValidate.isEmpty(sum)
                || !InputValidate.isInt(sum)
                || !InputValidate.isBiggerThanZero(Integer.parseInt(sum))
                || !InputValidate.isPossibleToTakeMoney(wallet.getBalance(), Integer.parseInt(sum)));

        // Запрашиваю идентификатор транзакции.
        System.out.println("Введите идентификатор транзакции:");
        do {
            id = ConsoleManager.readLine();
            if (id.equals("exit")) {
                nextState = new StartState();
                userService.createAction(wallet.getUser(), "Пользователь вышел из аккаунта");
                return;
            }
            if (id.equals("back")) {
                return;
            }
            if ((InputValidate.isInt(id)) && (transactionService.getTransactionById(Integer.parseInt(id)) != null)) {
                System.out.println("Транзакция с таким номером уже существует, повторите попытку.");
            }
        } while (InputValidate.isEmpty(id) || !InputValidate.isInt(id)
                || !InputValidate.isBiggerThanZero(Integer.parseInt(id))
                || transactionService.getTransactionById(Integer.parseInt(id)) != null);
        transactionService.createTransaction(new Transaction(Integer.parseInt(id), wallet.getUser().getLogin(),
                new Date(), wallet.getBalance(), -Integer.parseInt(sum), wallet.getBalance() - Integer.parseInt(sum)));
        walletService.takeMoneyFromBalance(wallet, Integer.parseInt(sum));
        userService.createAction(wallet.getUser(), "Пользователь снял суммую равную "
                + Integer.parseInt(sum) + " с баланса");
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
