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
//    private final UserClient userClient;
    @Override 
    public Wallet newWallet(){
        //Validate wallet

        //Ghi log

        Wallet wallet = Wallet.builder()
                        .walletId(UUID.randomUUID().toString())
                        .userId(UUID.randomUUID())
                        .balance(BigDecimal.ZERO)
                        .currency(configurationRepository.findByGroupConfigAndKey("CURRENCY", "VND").get().getValue())
                        .status(configurationRepository.findByGroupConfigAndKey("STATUS", "ACTIVE").get().getValue())
                        .build();
        walletRepository.save(wallet);

        return wallet;
    }


    @Override 
    public WalletResponse walletInfor(String wallet_id){
        if (wallet_id == null) {
            throw new IllegalArgumentException("Invalid Wallet ID");
        } 

        return formatResponse(wallet_id);
    }

    @Override
    public WalletResponse walletUpdateStatus(String wallet_id, WalletStatusRequest req){
        int updateRows = walletRepository.updateStatus(wallet_id, req.getStatus(), Instant.now());
        if(updateRows == 0){
            throw new RuntimeException("Update not found");
        }

        return walletInfor(wallet_id);
    }

    @Override
    public WalletResponse walletDeposit(WalletRequest req) {
        String walletId = req.getWalletId();

        if (req.getWalletId() == null) {
            throw new IllegalArgumentException("Invalid Wallet ID");
        }
        int updateRows = walletRepository.updateBalance(req.getWalletId(), req.getAmount(), Instant.now());
        if(updateRows == 0){
            throw new RuntimeException("Update not found");
        }

        return formatResponse(walletId);
    }

    @Override
    public WalletResponse walletWithdraw(WalletRequest req) {
        BigDecimal currentBalance = walletRepository.findById(req.getWalletId()).get().getBalance();
        BigDecimal amount = (req.getAmount().negate());
        String walletId = req.getWalletId();

        if (req.getWalletId() == null) {
            throw new IllegalArgumentException("Invalid Wallet ID");
        }

        //Kiểm tra số dư trong ví
        if(!hasSufficientFunds(currentBalance, req.getAmount())) {
            throw new RuntimeException("Insufficient funds");
        }

        int updateRows = walletRepository.updateBalance(req.getWalletId(), amount, Instant.now());

        if (updateRows == 0) {
            throw new RuntimeException("Update not found");
        }

        return formatResponse(walletId);
    }

    /**
     * Phương thức kiểm tra số dư trong ví
     * @param currentBalance, amount
     * @return trả về true nếu số dư đủ
     */
    public boolean hasSufficientFunds(BigDecimal currentBalance, BigDecimal amount) {
        return currentBalance.compareTo(amount) >= 0;
    }

    /**
     * Phương thức trả về dữ liệu dạng WalletResponse
     * @param walletId
     * @return
     */
    public WalletResponse formatResponse(String walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        String currency = configurationRepository.findByGroupConfigAndValue("CURRENCY", wallet.getCurrency()).get().getKey();
        String status = configurationRepository.findByGroupConfigAndValue("STATUS", wallet.getStatus()).get().getKey();

        return WalletResponse.builder()
                .walletId(wallet.getWalletId())
                .balance(wallet.getBalance())
                .currency(currency)
                .status(status)
                .createdAt(wallet.getCreatedAt())
                .updatedAt(wallet.getUpdatedAt())
                .build();
    }
}