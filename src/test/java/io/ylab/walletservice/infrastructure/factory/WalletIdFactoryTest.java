package io.ylab.walletservice.infrastructure.factory;

import io.ylab.walletservice.infrastucuture.factory.UserIdFactory;
import io.ylab.walletservice.infrastucuture.factory.WalletIdFactory;
import org.junit.Assert;
import org.junit.Test;

public class WalletIdFactoryTest {
    @Test
    public void methodGetNewWalletIdShouldReturnNewId() {
        int id1 = WalletIdFactory.getNewWalletId();
        int id2 = WalletIdFactory.getNewWalletId();
        Assert.assertNotEquals(id1, id2);
    }
}
