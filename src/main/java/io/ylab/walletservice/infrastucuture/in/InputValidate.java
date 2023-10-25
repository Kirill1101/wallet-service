package io.ylab.walletservice.infrastucuture.in;

import io.ylab.walletservice.model.User;

import java.util.Objects;

public class InputValidate {

    public static boolean isCorrectNumberInMenu(int startNumber, int endNumber, int inputNumber) {
        if (!(startNumber <= inputNumber && inputNumber <= endNumber)) {
            System.out.println("Некорректный ввод, повторите попытку.");
            return false;
        }
        return true;
    }

    public static boolean userIsExistsForLogin(User user) {
        if (user == null) {
            System.out.println("Такого пользователя не существует, повторите попытку.");
            return false;
        }
        return true;
    }

    public static boolean isCorrectPassword(String truePassword, String inputPassword) {
        if (!truePassword.equals(inputPassword)) {
            System.out.println("Пароль неверный, повторите попытку.");
            return false;
        }
        return true;
    }

    public static boolean isPossibleToTakeMoney(int balance, int sum) {
        if (sum > balance) {
            System.out.println("Нельзя снять больше денег, чем есть на счёте, повторите попытку.");
            return false;
        }
        return true;
    }

    public static boolean isBiggerThanZero(int num) {
        if (num <= 0) {
            System.out.println("Число должно быть строго больше нуля, повторите попытку.");
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String input) {
        if (Objects.equals(input, "")) {
            System.out.println("Ввод не может быть пустым, повторите попытку.");
            return true;
        }
        return false;
    }

    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Введите целое число.");
            return false;
        }
    }
}
