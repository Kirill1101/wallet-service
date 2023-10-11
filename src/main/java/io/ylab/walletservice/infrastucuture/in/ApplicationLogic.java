package io.ylab.walletservice.infrastucuture.in;

import io.ylab.walletservice.infrastucuture.in.state.ConsoleState;
import io.ylab.walletservice.infrastucuture.in.state.StartState;

/**
 * Класс, управляющий состояниями приложения.
 */
public class ApplicationLogic {
    private ConsoleState currentState;

    public ApplicationLogic() {
        this.currentState = new StartState();
    }

    /**
     * Запсукает процесс на текущем состоянии, затем меняет текущее состояние на следующие,
     * полученное благодаря ранее вызваному процессу.
     */
    public void process() {
        while (true) {
            try {
                currentState.process();
                if (currentState.nextState() != null) {
                    currentState = currentState.nextState();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}
