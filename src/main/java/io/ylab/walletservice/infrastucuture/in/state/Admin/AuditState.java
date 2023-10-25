package io.ylab.walletservice.infrastucuture.in.state.Admin;

import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.in.ConsoleManager;
import io.ylab.walletservice.infrastucuture.in.InputValidate;
import io.ylab.walletservice.infrastucuture.in.state.ConsoleState;
import io.ylab.walletservice.infrastucuture.in.state.StartState;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.services.UserService;

import java.util.List;

/**
 * Класс, реализующий консольную работу, позволяющую администратору выбирать пользователя
 * из списка и просматривать его историю действий.
 */
public class AuditState implements ConsoleState {

    private ConsoleState nextState;
    private final UserService userService;

    public AuditState() {
        this.userService = UserServiceFactory.getUserService();
        nextState = new AdminMenuState();;
    }

    @Override
    public void process() throws Exception {
        List<User> users = userService.getAllUsers();
        if (users.size() == 0) {
            System.out.println("Не существует ни одного пользователя.");
            return;
        }

        for (int i = 0; i < users.size(); ++i) {
            System.out.println(i + 1 + ". " + users.get(i).getLogin());
        }

        String input;
        ConsoleManager.separator();
        System.out.println("Введите номер пользовтеля, у которого хотите посмотреть аудит.");
        do {
            input = ConsoleManager.readLine();
            if (input.equals("exit")) {
                nextState = new StartState();
                return;
            }
            if (input.equals("back")) {
                return;
            }
        } while (InputValidate.isEmpty(input) || !InputValidate.isInt(input)
                || !InputValidate.isCorrectNumberInMenu(1, users.size(), Integer.parseInt(input)));

        int number = Integer.parseInt(input);
        for (String action : userService.getAllActions(users.get(number - 1))) {
            System.out.println("- " + action + ";");
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
