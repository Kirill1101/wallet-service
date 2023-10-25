package io.ylab.walletservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class Wallet {
    private final int id;
    private final String userLogin;
    @Setter
    private int balance;
}
