package io.ylab.walletservice.infrastucuture.in.state;

import io.ylab.walletservice.infrastucuture.in.state.LoginRegistration.LoginRegistrationChoiceState;

/**
 * Начальное окно приложения, выводит инструкцию по работе и перебрасыает в меню регистрации/авторизации.
 */
public class StartState implements ConsoleState{

    private ConsoleState nextState;

    @Override
    public void process() throws Exception {
        System.out.println("""
                Навигация в приложении осуществляется с помощью цифр.
                Для навигации программа принимает на вход только цифру без точек и других символов.
                Для того, чтобы выйти из акканута и переместится на стартовое меню введите "exit".
                Для того, чтобы вернутся на предыдущий пункт меню введите "back".
                Если вы введете "back" сразу после входа или регистрации, вы будете выброшены из аккаунта и
                перемещены на стартовое окно.
                Чтобы зайти в приложение в качестве администратора, введите "admin" в качестве логина и пароля.
                """);
        nextState = new LoginRegistrationChoiceState();
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
