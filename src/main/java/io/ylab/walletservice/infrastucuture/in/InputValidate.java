package io.ylab.walletservice.infrastucuture.in;

import io.ylab.walletservice.model.User;

import java.util.Objects;

/**
 * Класс для валидации данных, поулченных из консоли.
 */
public class InputValidate {

    /**
     * Метод, необходимый для проверки корректности введеного пользователем числа при навигации в меню.
     * @param startNumber Первый номер в списке.
     * @param endNumber Последний номер в списке.
     * @param inputNumber Номер, введный пользователем.
     * @return Возвращает boolean, который указывает находится ли пользовательское число в диапазоне.
     */
    public static boolean isCorrectNumberInList(int startNumber, int endNumber, int inputNumber) {
        if (!(startNumber <= inputNumber && inputNumber <= endNumber)) {
            System.out.println("Некорректный ввод, повторите попытку.");
            return false;
        }
        return true;
    }

    /**
     * Метод, необходимый для проверки существования пользователя при регистрации, и для вывода сообщения
     * о несуществющем логине.
     * @param user Пользователь
     * @return Возвращает boolean, который указывает существует ли пользователь.
     */
    public static boolean userIsExistsForLogin(User user) {
        if (user == null) {
            System.out.println("Такого пользователя не существует, повторите попытку.");
            return false;
        }
        return true;
    }

    /**
     * Метод необходимый для проверки корректности пароля и вывода сообщения о некорретктности пароля.
     * @param truePassword Правильный пароль.
     * @param inputPassword Пароль, который ввел пользователь.
     * @return Возвращает boolean, который указывает корректен ли пароль.
     */
    public static boolean isCorrectPassword(String truePassword, String inputPassword) {
        if (!truePassword.equals(inputPassword)) {
            System.out.println("Пароль неверный, повторите попытку.");
            return false;
        }
        return true;
    }

    /**
     * Метод, который првоеряет хватает ли денег на счете, для того чтобы снять запрашиваемую сумму.
     * @param balance Текущий баланс.
     * @param sum Сумма, которую пользователь хочет снять.
     * @return Возвращает boolean, который указывает можно ли снять со счета запрашиваемую сумму.
     */
    public static boolean isPossibleToTakeMoney(int balance, int sum) {
        if (sum > balance) {
            System.out.println("Нельзя снять больше денег, чем есть на счёте, повторите попытку.");
            return false;
        }
        return true;
    }

    /**
     * Метод, который проверяет больше ли чилсо нуля и выводит сообщение если меньше.
     * @param num Число, которое необходимо проверить.
     * @return Возвращает boolean, который указывает больше ли число нуля.
     */
    public static boolean isBiggerThanZero(int num) {
        if (num <= 0) {
            System.out.println("Число должно быть строго больше нуля, повторите попытку.");
            return false;
        }
        return true;
    }

    /**
     * Метод, необходимый для того, чтобы проверить ввел ли пользователь пустую строку.
     * Т.е исключает ошибки связанные с тем, что пользователь нажал enter, ничего не введя в консоль.
     * @param input Ввод пользователя.
     * @return Возвращает boolean, который указывает пустая ли строка.
     */
    public static boolean isEmpty(String input) {
        if (Objects.equals(input, "")) {
            System.out.println("Ввод не может быть пустым, повторите попытку.");
            return true;
        }
        return false;
    }

    /**
     * Метод проверяет является ли строка целым числом.
     * @param input Ввод пользователя.
     * @return Возвращает boolean, который указывает является ли строка целым числом.
     */
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
