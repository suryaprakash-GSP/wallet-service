package com.gsp.wallet_service.controller;

import com.gsp.wallet_service.dto.WalletCreateRequest;
import com.gsp.wallet_service.dto.WalletResponse;
import com.gsp.wallet_service.entity.Wallet;
import com.gsp.wallet_service.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {


    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<WalletResponse> createWallet(@Valid @RequestBody WalletCreateRequest walletCreateRequest) {
        Wallet wallet = walletService.createWallet(walletCreateRequest.getOwnerName());

        WalletResponse response = new WalletResponse(
                wallet.getId(),
                wallet.getOwnerName(),
                wallet.getBalance(),
                wallet.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable Long id) {
        Wallet wallet = walletService.getWallet(id);
        WalletResponse response = new WalletResponse(
                wallet.getId(),
                wallet.getOwnerName(),
                wallet.getBalance(),
                wallet.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }
}
