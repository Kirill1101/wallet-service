package io.ylab.walletservice.infrastucuture.in;

import java.util.Scanner;

/**
 * Класс, необходимый для того, чтобы не создавать каждый раз новый scanner.
 * Также содержит метод, устанавливающий разделить между логическами блоками в консоли.
 */
public class ConsoleManager {
    private static final Scanner scanner = new Scanner(System.in);
    public static String readLine() {
        return scanner.nextLine().trim();
    }

    public static void separator() {
        System.out.print("\n-------------------------------------\n");
    }
}
