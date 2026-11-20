package com.gsp.wallet_service.service;

import com.gsp.wallet_service.entity.Wallet;
import com.gsp.wallet_service.exception.WalletNotFoundException;
import com.gsp.wallet_service.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Wallet createWallet(String ownerName) {
        Wallet wallet = new Wallet();
        wallet.setOwnerName(ownerName);
        return walletRepository.save(wallet);

    }

    @Transactional(readOnly = true)
    public Wallet getWallet(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() ->
                new WalletNotFoundException("Wallet not found with id:" + walletId));
    }

    @Transactional
    public void deposit(Long walletId, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found with id:" + walletId));
        wallet.credit(amount);

    }

    @Transactional
    public void withdraw(Long walletId, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found with id:" + walletId));
        wallet.debit(amount);
    }

}
