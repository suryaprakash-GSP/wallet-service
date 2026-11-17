
package com.gsp.wallet_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WalletResponse {

    private Long id;
    private String ownerName;

    private BigDecimal balance;
    private LocalDateTime createdAt;

    public WalletResponse(
            Long id,
            String ownerName,
            BigDecimal balance,
            LocalDateTime createdAt) {

        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
