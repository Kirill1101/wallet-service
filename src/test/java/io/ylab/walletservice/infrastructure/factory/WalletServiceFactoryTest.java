package io.ylab.walletservice.infrastructure.factory;

import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.WalletServiceFactory;
import io.ylab.walletservice.services.UserService;
import io.ylab.walletservice.services.WalletService;
import org.junit.Assert;
import org.junit.Test;

public class WalletServiceFactoryTest {
    @Test
    public void methodGetWalletServiceShouldReturnUserService() {
        WalletService walletService = WalletServiceFactory.getWalletService();
        Assert.assertEquals(walletService.getClass(), WalletService.class);
    }

    @Test
    public void methodGetWalletServiceShouldReturnEqualServiceInDifferentCalls() {
        WalletService walletService1 = WalletServiceFactory.getWalletService();
        WalletService walletService2 = WalletServiceFactory.getWalletService();
        Assert.assertEquals(walletService1, walletService2);
    }
}
