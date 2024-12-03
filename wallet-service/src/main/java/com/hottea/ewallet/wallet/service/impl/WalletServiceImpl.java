package com.hottea.ewallet.wallet.service.impl;

import com.hottea.ewallet.wallet.dto.response.user.UserClient;
import com.hottea.ewallet.wallet.dto.response.wallet.WalletResponseToTransaction;
import com.hottea.ewallet.wallet.repository.ConfigurationRepository;
import com.hottea.ewallet.wallet.repository.WalletRepository;
import com.hottea.ewallet.wallet.service.WalletService;

import lombok.AllArgsConstructor;

import com.hottea.ewallet.wallet.dto.request.wallet.WalletRequest;
import com.hottea.ewallet.wallet.dto.request.wallet.WalletStatusRequest;
import com.hottea.ewallet.wallet.dto.response.wallet.WalletResponse;
import com.hottea.ewallet.wallet.entity.Wallet;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
/**
 * WalletServiceImpl là lớp triển khai logic nghiệp vụ
 * @author namhm
 * @verion 1.0
 * @since 25-11-2024 
 */
@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final ConfigurationRepository configurationRepository;
    private final UserClient userClient;
    @Override 
    public Wallet newWallet(){
        //Validate wallet

        //Ghi log

        Wallet wallet = new Wallet();
        wallet.setUserId(userClient.getUserById(id).getId());
        wallet.setWalletId(UUID.randomUUID().toString());
//        wallet.setUserId(UUID.randomUUID());   // sinh tạm user_id
        wallet.setBalance(BigDecimal.ZERO);    // khởi tạo balance = 0
        wallet.setCurrency(configurationRepository.findByGroupConfigAndKey("CURRENCY", "VND").get().getValue());
        wallet.setStatus(configurationRepository.findByGroupConfigAndKey("STATUS", "ACTIVE").get().getValue());
        return walletRepository.save(wallet); 
    }


    @Override 
    public WalletResponse walletInfor(String wallet_id){
        if (wallet_id == null) {
            throw new IllegalArgumentException("Invalid Wallet ID");
        } 
        //validate req
        return walletRepository.findWallet(wallet_id)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Override
    public WalletResponse walletUpdateStatus(String wallet_id, WalletStatusRequest req){
        int updateRows = walletRepository.updateStatus(wallet_id, req.getStatus(), Instant.now());
        if(updateRows == 0){
            throw new RuntimeException("Update not found");
        }
        //validate req
        return walletRepository.findWallet(wallet_id)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));                
    }

    @Override
    public WalletResponseToTransaction walletUpdateBalance(WalletRequest req) {
        if (req.getWalletId() == null) {
            throw new IllegalArgumentException("Invalid Wallet ID");
        }

        int updateRows = walletRepository.updateBalance(req.getWalletId(), req.getAmount(), Instant.now());
        if(updateRows == 0){
            throw new RuntimeException("Update not found");
        }

        return new WalletResponseToTransaction("Update balance successfully", 200);
    }
}