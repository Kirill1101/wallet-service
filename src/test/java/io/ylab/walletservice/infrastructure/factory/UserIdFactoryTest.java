package io.ylab.walletservice.infrastructure.factory;

import io.ylab.walletservice.infrastucuture.factory.UserIdFactory;
import org.junit.Assert;
import org.junit.Test;

public class UserIdFactoryTest {
    @Test
    public void methodGetNewUserIdShouldReturnNewId() {
        int id1 = UserIdFactory.getNewUserId();
        int id2 = UserIdFactory.getNewUserId();
        Assert.assertNotEquals(id1, id2);
    }
}
