package com.gsp.wallet_service.service;


import com.gsp.wallet_service.entity.Wallet;
import com.gsp.wallet_service.exception.WalletNotFoundException;
import com.gsp.wallet_service.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;
    @InjectMocks
    private WalletService walletService;

    @Test
    void createWallet_shouldReturnSavedWallet() {
        when(walletRepository.save(any(Wallet.class)))
                .thenAnswer(invocation -> {
                    Wallet savedWallet = invocation.getArgument(0);
                    savedWallet.setId(1L);
                    return savedWallet;
                });
        Wallet result = walletService.createWallet("Surya");

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getWallet_shouldReturnWalletWhenFound() {
        when(walletRepository.findById(any(Long.class))).thenAnswer(
                invocation -> {
                    Long id = invocation.getArgument(0);
                    Wallet wallet = new Wallet();
                    wallet.setId(id);
                    wallet.setOwnerName("Surya");
                    return Optional.of(wallet);

                }
        );
        Wallet result = walletService.getWallet(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Surya", result.getOwnerName());


    }

    @Test
    void getWallet_shouldThrowExceptionWhenNotFound() {

        when(walletRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        WalletNotFoundException exception = assertThrows(
                WalletNotFoundException.class,
                () -> walletService.getWallet(1L)
        );

        assertEquals("Wallet not found with id:1", exception.getMessage());

    }


}
