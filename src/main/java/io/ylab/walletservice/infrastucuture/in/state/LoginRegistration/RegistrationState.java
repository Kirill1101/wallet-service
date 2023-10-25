package io.ylab.walletservice.infrastucuture.in.state.LoginRegistration;

import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.in.ConsoleManager;
import io.ylab.walletservice.infrastucuture.in.InputValidate;
import io.ylab.walletservice.infrastucuture.in.state.ConsoleState;
import io.ylab.walletservice.infrastucuture.in.state.StartState;
import io.ylab.walletservice.infrastucuture.in.state.Wallet.WalletState;
import io.ylab.walletservice.model.Role;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.services.UserService;

import java.util.ArrayList;

/**
 * Класс, который реализует регистрацию пользователя с помощью консольного ввода.
 */
public class RegistrationState implements ConsoleState {

    private ConsoleState nextState;

    private final UserService userService;

    public RegistrationState() {
        this.userService = UserServiceFactory.getUserService();
        nextState = new StartState();
    }

    @Override
    public void process() throws Exception {
        ConsoleManager.separator();
        System.out.println("Регистрация.");

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
            if (user != null) {
                System.out.println("Такой пользователь уже существует, повторите попытку.");
            }
        } while (InputValidate.isEmpty(login) || (user != null));

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
            if (!password.isEmpty()) {
                userService.createUser(login, password, Role.User);
                user = userService.getUserByLogin(login);
                nextState = new WalletState(user);
                userService.createAction(user, "Пользователь зарегестрировался");
            }
        } while (InputValidate.isEmpty(password));

    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
