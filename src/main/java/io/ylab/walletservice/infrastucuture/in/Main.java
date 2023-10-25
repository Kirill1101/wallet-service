package io.ylab.walletservice.infrastucuture.in;

import io.ylab.walletservice.infrastucuture.factory.TransactionServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.infrastucuture.factory.WalletServiceFactory;
import io.ylab.walletservice.infrastucuture.liquibase.Liquibase;
import io.ylab.walletservice.infrastucuture.repository_impl.WalletRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        Liquibase.liquibaseStart();
        UserServiceFactory.getUserService();
        WalletServiceFactory.getWalletService();
        TransactionServiceFactory.getTransactionService();
        new ApplicationLogic().process();
    }
}
