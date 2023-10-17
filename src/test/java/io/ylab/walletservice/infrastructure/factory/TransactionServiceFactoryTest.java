package io.ylab.walletservice.infrastructure.factory;

import io.ylab.walletservice.infrastucuture.factory.TransactionServiceFactory;
import io.ylab.walletservice.services.TransactionService;
import org.junit.Assert;
import org.junit.Test;

public class TransactionServiceFactoryTest {
    @Test
    public void methodGetTransactionServiceShouldReturnTransactionService() {
        TransactionService transactionService = TransactionServiceFactory.getTransactionService();
        Assert.assertEquals(transactionService.getClass(), TransactionService.class);
    }

    @Test
    public void methodGetTransactionServiceShouldReturnEqualServiceInDifferentCalls() {
        TransactionService transactionService1 = TransactionServiceFactory.getTransactionService();
        TransactionService transactionService2 = TransactionServiceFactory.getTransactionService();
        Assert.assertEquals(transactionService1, transactionService2);
    }
}
