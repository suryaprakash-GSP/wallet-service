package com.gsp.wallet_service.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class WalletTest {


    @Test
    void credit_shouldIncreaseBalance() {
        Wallet wallet = new Wallet();
        wallet.credit(new BigDecimal("500"));
        assertEquals(new BigDecimal("500"), wallet.getBalance());
    }

    @Test
    void credit_shouldThrowExceptionWhenAmountIsZero() {
        Wallet wallet = new Wallet();

        assertThrows(IllegalArgumentException.class,
                () -> wallet.credit(BigDecimal.ZERO));


    }

    @Test
    void credit_shouldThrowExceptionWhenAmountIsNegative() {
        Wallet wallet = new Wallet();
        assertThrows(IllegalArgumentException.class,
                () -> wallet.credit(new BigDecimal("-1")));
    }

    @Test
    void debit_shouldDecreaseBalance() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("1000"));
        wallet.debit(new BigDecimal("300"));
        assertEquals(new BigDecimal("700"), wallet.getBalance());
    }


    @Test
    void debit_shouldThrowExceptionWhenAmountIsZero() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("1000.00"));
        assertThrows(IllegalArgumentException.class,
                () -> wallet.debit(BigDecimal.ZERO));


    }

    @Test
    void debit_shouldThrowExceptionWhenAmountIsNegative() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("1000.00"));
        assertThrows(IllegalArgumentException.class,
                () -> wallet.debit(new BigDecimal("-1")));
    }

    @Test
    void debit_shouldAllowWithdrawalOfExactBalance() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("1000"));
        wallet.debit(new BigDecimal("1000"));
        assertEquals(BigDecimal.ZERO, wallet.getBalance());
    }

    @Test
    void debit_shouldThrowExceptionWhenAmountExceedsBalance() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("1000"));

        assertThrows(IllegalArgumentException.class,
                () -> wallet.debit(new BigDecimal("1100")));
        assertEquals(new BigDecimal("1000"), wallet.getBalance());
    }

    @Test
    void credit_shouldThrowExceptionWhenAmountIsNull() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("1000"));
        assertThrows(IllegalArgumentException.class,
                () -> wallet.credit(null));
        assertEquals(new BigDecimal("1000"), wallet.getBalance());
    }

    @Test
    void debit_shouldThrowExceptionWhenAmountIsNull() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("1000"));
        assertThrows(IllegalArgumentException.class,
                () -> wallet.debit(null));
        assertEquals(new BigDecimal("1000"), wallet.getBalance());
    }

    @Test
    void wallet_shouldStartWithZeroBalance() {
        Wallet wallet = new Wallet();
        assertEquals(BigDecimal.ZERO, wallet.getBalance());
    }

    @Test
    void debit_shouldNotChangeBalanceWhenWithdrawalFails() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("1000"));
        assertThrows(IllegalArgumentException.class,
                () -> wallet.debit(new BigDecimal("1500")));
        assertEquals(new BigDecimal("1000"), wallet.getBalance());
    }
}
