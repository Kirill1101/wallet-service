package io.ylab.walletservice.infrastucuture.in.state.Admin;

import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.in.ConsoleManager;
import io.ylab.walletservice.infrastucuture.in.InputValidate;
import io.ylab.walletservice.infrastucuture.in.state.ConsoleState;
import io.ylab.walletservice.infrastucuture.in.state.LoginRegistration.LoginRegistrationChoiceState;
import io.ylab.walletservice.infrastucuture.in.state.StartState;
import io.ylab.walletservice.services.UserService;

/**
 * Класс, реализующий консольную работу с меню администратоора.
 */
public class AdminMenuState implements ConsoleState {

    private ConsoleState nextState;
    private final UserService userService;

    public AdminMenuState() {
        this.userService = UserServiceFactory.getUserService();
        nextState = new StartState();
    }

    public void process() throws Exception {
        ConsoleManager.separator();
        System.out.println("1. Аудит действий игроков.");
        String input;
        do {
            input = ConsoleManager.readLine();
        } while (InputValidate.isEmpty(input));
        switch (input) {
            case "1":
                nextState = new AuditState();
                break;
            case "back":
                nextState = new LoginRegistrationChoiceState();
                return;
            case "exit":
                return;
            default:
                System.out.println("Некорректный ввод. Введите цифру.");
                break;
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
