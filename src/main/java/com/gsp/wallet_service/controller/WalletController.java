package com.gsp.wallet_service.controller;

import com.gsp.wallet_service.dto.WalletCreateRequest;
import com.gsp.wallet_service.entity.Wallet;
import com.gsp.wallet_service.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {


    private final WalletService walletService;
    public WalletController(WalletService walletService)
    {
        this.walletService=walletService;
    }

    @PostMapping
    public Wallet createWallet(@Valid @RequestBody WalletCreateRequest walletCreateRequest) {
        return walletService.createWallet(walletCreateRequest.getOwnerName());
    }
    @GetMapping("/{id}")
    public Wallet getWallet(@PathVariable Long id) {
        return walletService.getWallet(id);
    }
}
