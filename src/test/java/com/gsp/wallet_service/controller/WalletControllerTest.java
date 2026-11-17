package com.gsp.wallet_service.controller;

import com.gsp.wallet_service.service.WalletService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(WalletController.class)
public class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WalletService walletService;

    @InjectMocks
    private WalletController walletController;

    @Test
    void createWallet_shouldReturn400WhenOwnerNameIsBlank() throws Exception {

        mockMvc.perform(post("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "ownerName":"  "
                        }
                        """)
        ).andExpect(status().isBadRequest());
    }

}
