package io.ylab.walletservice.infrastructure.factory;

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
