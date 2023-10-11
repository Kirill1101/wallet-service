package io.ylab.walletservice.infrastucuture.in.state;

/**
 * Интерфейс, необходимый для создания состояний консольного приложения и удобной работе с ними.
 * Каждое состояние устанавливает следущее состояние, которое необходимо открыть в ходе работы приложения.
 */
public interface ConsoleState {
    void process() throws Exception;
    ConsoleState nextState();
}
