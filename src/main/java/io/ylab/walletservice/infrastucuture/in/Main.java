package io.ylab.walletservice.infrastucuture.in;

/**
 * Класс, точка входа в программу.
 */
public class Main {
    /**
     * Вызывается метод process класса ApplicationLogic, что служит началом работой с консолью.
     * @param args
     */
    public static void main(String[] args) {
        new ApplicationLogic().process();
    }
}
