package io.ylab.walletservice.infrastucuture.in.state.Wallet;

import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.WalletIdFactory;
import io.ylab.walletservice.infrastucuture.factory.WalletServiceFactory;
import io.ylab.walletservice.infrastucuture.in.ConsoleManager;
import io.ylab.walletservice.infrastucuture.in.InputValidate;
import io.ylab.walletservice.infrastucuture.in.state.ConsoleState;
import io.ylab.walletservice.infrastucuture.in.state.LoginRegistration.LoginRegistrationChoiceState;
import io.ylab.walletservice.infrastucuture.in.state.StartState;
import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.model.Wallet;
import io.ylab.walletservice.services.UserService;
import io.ylab.walletservice.services.WalletService;

import java.util.ArrayList;

/**
 * Класс, который реализует работу кошелька с помощью консоли.
 */
public class WalletState implements ConsoleState {

    private ConsoleState nextState;
    private final WalletService walletService;
    private final UserService userService;
    private final User user;
    private Wallet wallet;

    public WalletState(User user) {
        this.walletService = WalletServiceFactory.getWalletService();
        this.userService = UserServiceFactory.getUserService();
        this.user = user;
        nextState = new StartState();
    }


    @Override
    public void process() throws Exception {
        // Проверяю, создан ли кошелек у данного пользователя.
        if (walletService.getWalletByLogin(user.getLogin()) == null) {
            wallet = new Wallet(WalletIdFactory.getNewWalletId(), user, 0, new ArrayList<Transaction>());
            walletService.createWallet(wallet);
        } else {
            wallet = walletService.getWalletByLogin(user.getLogin());
        }

        ConsoleManager.separator();
        System.out.println("Пользователь: " + user.getLogin());
        System.out.println("Баланс: " + wallet.getBalance());
        System.out.println("1. Пополнить ");
        System.out.println("2. Снять ");
        System.out.println("3. Просмотреть историю транзакций");

        String input;
        do {
            input = ConsoleManager.readLine();
        } while (InputValidate.isEmpty(input));
        switch (input) {
            case "1":
                nextState = new CreditState(wallet);
                break;
            case "2":
                nextState = new DebitState(wallet);
                break;
            case "3":
                nextState = new TransactionHistoryState(wallet);
                break;
            case "back":
                nextState = new LoginRegistrationChoiceState();
                userService.createAction(user, "Пользователь вышел из аккаунта");
                return;
            case "exit":
                userService.createAction(user, "Пользователь вышел из аккаунта");
                return;
            default:
                System.out.println("Некорректный ввод. Введите \"1\", \"2\" или \"3\".");
                break;
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
