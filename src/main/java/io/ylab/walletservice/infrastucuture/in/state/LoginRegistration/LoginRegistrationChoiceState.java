package io.ylab.walletservice.infrastucuture.in.state.LoginRegistration;

import io.ylab.walletservice.infrastucuture.in.ConsoleManager;
import io.ylab.walletservice.infrastucuture.in.InputValidate;
import io.ylab.walletservice.infrastucuture.in.state.ConsoleState;
import io.ylab.walletservice.infrastucuture.in.state.StartState;

/**
 * Класс, реализующий выбор пользователем регистрации или авторизации
 * посредством консольного ввода.
 */
public class LoginRegistrationChoiceState implements ConsoleState {

    private ConsoleState nextState;

    public LoginRegistrationChoiceState() {
        nextState = new StartState();
    }

    @Override
    public void process() throws Exception {
        ConsoleManager.separator();
        System.out.println("1. Войти");
        System.out.println("2. Зарегестрироваться");
        String input;
        do {
            input = ConsoleManager.readLine();
        } while (InputValidate.isEmpty(input));
        switch (input) {
            case "1":
                nextState = new LoginState();
                break;
            case "2":
                nextState = new RegistrationState();
                break;
            default:
                System.out.println("Некорректный ввод. Введите \"1\" или \"2\"");
                break;
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
