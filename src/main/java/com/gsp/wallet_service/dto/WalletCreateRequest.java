package com.gsp.wallet_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class WalletCreateRequest {


    @NotBlank(message = "Owner Name is Required")
    @Size(max = 100, message = "Owner name cannot exceed 100 characters")
    private String ownerName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
