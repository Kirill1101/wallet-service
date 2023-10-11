package io.ylab.walletservice.infrastructure.factory;

import io.ylab.walletservice.infrastucuture.factory.TransactionServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.services.TransactionService;
import io.ylab.walletservice.services.UserService;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceFactoryTest {
    @Test
    public void methodGetUserServiceShouldReturnUserService() {
        UserService userService = UserServiceFactory.getUserService();
        Assert.assertEquals(userService.getClass(), UserService.class);
    }

    @Test
    public void methodGetUserServiceShouldReturnEqualServiceInDifferentCalls() {
        UserService userService1 = UserServiceFactory.getUserService();
        UserService userService2 = UserServiceFactory.getUserService();
        Assert.assertEquals(userService1, userService2);
    }
}
