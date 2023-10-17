package io.ylab.walletservice.infrastucuture.in.state.LoginRegistration;

import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.in.ConsoleManager;
import io.ylab.walletservice.infrastucuture.in.InputValidate;
import io.ylab.walletservice.infrastucuture.in.state.Admin.AdminMenuState;
import io.ylab.walletservice.infrastucuture.in.state.ConsoleState;
import io.ylab.walletservice.infrastucuture.in.state.StartState;
import io.ylab.walletservice.infrastucuture.in.state.Wallet.WalletState;
import io.ylab.walletservice.model.Admin;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.services.UserService;

/**
 * Класс, который реализует авторизацию пользователя с помощью консольного ввода.
 */
public class LoginState implements ConsoleState {

    private ConsoleState nextState;
    private final UserService userService;

    public LoginState() {
        this.userService = UserServiceFactory.getUserService();
        nextState = new StartState();
    }

    @Override
    public void process() throws Exception {
        System.out.println("Авторизация.");

        // Обработка ввода логина.
        System.out.println("Введите логин:");
        String login, password;
        User user;
        do {
            login = ConsoleManager.readLine();
            if (login.equals("exit")) {
                return;
            }
            if (login.equals("back")) {
                nextState = new LoginRegistrationChoiceState();
                return;
            }
            user = userService.getUserByLogin(login);
        } while (InputValidate.isEmpty(login) || !(InputValidate.userIsExistsForLogin(user)));

        // Обработка ввода пароля.
        System.out.println("Введите пароль:");
        do {
            password = ConsoleManager.readLine();
            if (password.equals("exit")) {
                return;
            }
            if (password.equals("back")) {
                nextState = new LoginRegistrationChoiceState();
                return;
            }
        } while (InputValidate.isEmpty(password) || !InputValidate.isCorrectPassword(user.getPassword(), password));
        if (user instanceof Admin) {
            nextState = new AdminMenuState();
        } else {
            nextState = new WalletState(user);
        }
        userService.createAction(user, "Пользователь авторизировался");
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
