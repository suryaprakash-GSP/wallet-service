package com.gsp.wallet_service.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class WalletTransactionRequest {

    @NotNull(message = "Amount is required")
    @DecimalMax(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
