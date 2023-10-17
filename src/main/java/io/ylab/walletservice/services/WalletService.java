package io.ylab.walletservice.services;

import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.model.Wallet;
import io.ylab.walletservice.repository.WalletRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

/**
 * Сервис обеспечивающий логику работы с кошельком.
 */
@AllArgsConstructor
public class WalletService {
    WalletRepository walletRepository;

    /**
     * Создание кошелька.
     * @param wallet Кошелек.
     */
    public void createWallet(Wallet wallet) {
        walletRepository.saveWallet(wallet);
    }

    /**
     * Получить кошелек пользователя по его логину.
     * @param login Логин.
     * @return Возвращает кошелек пользователя.
     */
    public Wallet getWalletByLogin(String login) {
        return walletRepository.getWalletByLogin(login);
    }

    /**
     * Добавление денег на баланс.
     * @param wallet Кошелек, на который надо положить деньги.
     * @param money Сколько денег надо положить.
     */
    public void addMoneyOnBalance(Wallet wallet, int money) {
        wallet.setBalance(wallet.getBalance() + money);
    }

    /**
     * Снятие денег с кошелька.
     * @param wallet Кошелек, с которого надо снять деньги.
     * @param money Сколько денег надо снять.
     * @return Возвращает boolean, если денег недостаточно.
     */
    public boolean takeMoneyFromBalance(Wallet wallet, int money) {
        if (money > wallet.getBalance()) {
            return false;
        } else {
            wallet.setBalance(wallet.getBalance() - money);
            return true;
        }
    }

    /**
     * Получение всех транзакций пользователя.
     * @param wallet Кошелек, с которого были осуществлены транзакции.
     * @return Возвращает список всех транзакций.
     */
    public ArrayList<Transaction> getAllTransactionFromUser(Wallet wallet) {
        return wallet.getTransactions();
    }

    /**
     * Добавляет транзакцию в список всех транзакций.
     * @param wallet На каком кошельке было осуществлена транакция.
     * @param transaction Транзакция.
     */
    public void addTransactionToListOfTransactions(Wallet wallet, Transaction transaction) {
        wallet.getTransactions().add(transaction);
    }
}
