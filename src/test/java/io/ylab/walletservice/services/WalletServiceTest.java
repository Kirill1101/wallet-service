package io.ylab.walletservice.services;

import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.WalletServiceFactory;
import io.ylab.walletservice.model.User;
import io.ylab.walletservice.model.Wallet;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class WalletServiceTest {
    @Test
    public void methodCreateWalletShouldCreateWallet() {
        UserService userService = UserServiceFactory.getUserService();
        User user = new User(UserIdFactory.getNewUserId(), "kirill", "1234", new ArrayList<>());
        userService.createUser(user);

        WalletService walletService = WalletServiceFactory.getWalletService();
        Wallet wallet = new Wallet(WalletIdFactory.getNewWalletId(), user, 0, new ArrayList<>());
        walletService.createWallet(wallet);

        Assert.assertNotNull(walletService.getWalletByLogin("kirill"));
    }

    @Test
    public void methodGetWalletByLoginShouldReturnCorrectWallet() {
        UserService userService = UserServiceFactory.getUserService();
        User user = new User(UserIdFactory.getNewUserId(), "kirill", "1234", new ArrayList<>());
        userService.createUser(user);

        WalletService walletService = WalletServiceFactory.getWalletService();
        Wallet wallet = new Wallet(WalletIdFactory.getNewWalletId(), user, 0, new ArrayList<>());
        walletService.createWallet(wallet);

        Assert.assertEquals(wallet, walletService.getWalletByLogin("kirill"));
    }

    @Test
    public void methodAddMoneyOnBalanceShouldAddMoneyCorrectly() {
        UserService userService = UserServiceFactory.getUserService();
        User user = new User(UserIdFactory.getNewUserId(), "kirill", "1234", new ArrayList<>());
        userService.createUser(user);

        WalletService walletService = WalletServiceFactory.getWalletService();
        Wallet wallet = new Wallet(WalletIdFactory.getNewWalletId(), user, 0, new ArrayList<>());
        walletService.createWallet(wallet);

        walletService.addMoneyOnBalance(wallet, 10);
        Assert.assertEquals(10, wallet.getBalance());
    }

    @Test
    public void methodTakeMoneyFromBalanceShouldTakeMoneyCorrectly() {
        UserService userService = UserServiceFactory.getUserService();
        User user = new User(UserIdFactory.getNewUserId(), "kirill", "1234", new ArrayList<>());
        userService.createUser(user);

        WalletService walletService = WalletServiceFactory.getWalletService();
        Wallet wallet = new Wallet(WalletIdFactory.getNewWalletId(), user, 10, new ArrayList<>());
        walletService.createWallet(wallet);

        walletService.takeMoneyFromBalance(wallet, 6);
        Assert.assertEquals(4, wallet.getBalance());
    }
}
