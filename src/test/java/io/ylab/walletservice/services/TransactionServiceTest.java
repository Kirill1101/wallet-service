package io.ylab.walletservice.services;

import io.ylab.walletservice.infrastucuture.factory.*;
import io.ylab.walletservice.infrastucuture.repository_impl.TransactionRepositoryImpl;
import io.ylab.walletservice.infrastucuture.repository_impl.UserRepositoryImpl;
import io.ylab.walletservice.infrastucuture.repository_impl.WalletRepositoryImpl;
import io.ylab.walletservice.model.Transaction;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.model.Wallet;
import io.ylab.walletservice.repository.TransactionRepository;
import io.ylab.walletservice.repository.UserRepository;
import io.ylab.walletservice.repository.WalletRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TransactionServiceTest {
    @Test
    public void methodGetTransactionByIdShouldCorrectTransaction() {
        UserService userService = UserServiceFactory.getUserService();
        User user = new User(UserIdFactory.getNewUserId(), "kirill", "1234", new ArrayList<>());
        userService.createUser(user);

        WalletService walletService = WalletServiceFactory.getWalletService();
        Wallet wallet = new Wallet(WalletIdFactory.getNewWalletId(), user, 0, new ArrayList<>());
        walletService.createWallet(wallet);

        TransactionService transactionService = TransactionServiceFactory.getTransactionService();
        Transaction transaction = (new Transaction(1, "kirill", new Date(),
                10, 2, 12));
        transactionService.createTransaction(transaction);
        Assert.assertEquals(transactionService.getTransactionById(1), transaction);
    }

    @Test
    public void methodCreateTransactionShouldCreateTransaction() {
        UserService userService = UserServiceFactory.getUserService();
        User user = new User(UserIdFactory.getNewUserId(), "kirill", "1234", new ArrayList<>());
        userService.createUser(user);

        WalletService walletService = WalletServiceFactory.getWalletService();
        Wallet wallet = new Wallet(WalletIdFactory.getNewWalletId(), user, 0, new ArrayList<>());
        walletService.createWallet(wallet);

        TransactionService transactionService = TransactionServiceFactory.getTransactionService();
        Transaction transaction = (new Transaction(1, "kirill", new Date(),
                10, 2, 12));
        transactionService.createTransaction(transaction);
        Assert.assertNotNull(transactionService.getTransactionById(1));
    }

    @Test
    public void methodGetInfoFromTransactionAsStringShouldReturnCorrectValue() {
        UserService userService = UserServiceFactory.getUserService();
        User user = new User(UserIdFactory.getNewUserId(), "kirill", "1234", new ArrayList<>());
        userService.createUser(user);

        WalletService walletService = WalletServiceFactory.getWalletService();
        Wallet wallet = new Wallet(WalletIdFactory.getNewWalletId(), user, 0, new ArrayList<>());
        walletService.createWallet(wallet);

        TransactionService transactionService = TransactionServiceFactory.getTransactionService();
        Date date = new Date();
        Transaction transaction = (new Transaction(1, "kirill", date,
                10, 2, 12));
        transactionService.createTransaction(transaction);
        String correctAnswer = "ID транзакции: 1; Пользователь: kirill; Время: " + date + "; До: 10; Изменение: 2; После: 12";
        Assert.assertEquals(transactionService.getInfoFromTransactionAsString(transaction), correctAnswer);
    }
}
