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

        Wallet wallet = new Wallet();
//        wallet.setUserId(userClient.getUserById(id).getId()); Lấy số user_id từ UserService
        wallet.setWalletId(UUID.randomUUID().toString());
        wallet.setUserId(UUID.randomUUID());   // sinh tạm user_id
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
    public WalletResponse walletDeposit(WalletRequest req) {
        if (req.getWalletId() == null) {
            throw new IllegalArgumentException("Invalid Wallet ID");
        }
        int updateRows = walletRepository.updateBalance(req.getWalletId(), req.getAmount(), Instant.now());
        if(updateRows == 0){
            throw new RuntimeException("Update not found");
        }
        return walletRepository.findWallet(req.getWalletId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Override
    public WalletResponse walletWithdraw(WalletRequest req) {
        BigDecimal currentBalance = walletRepository.findWallet(req.getWalletId()).get().getBalance();
        BigDecimal amount = (req.getAmount().negate());

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

        return walletRepository.findWallet(req.getWalletId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    /**
     * Phương thức kiểm tra số dư trong ví
     * @param currentBalance, amount
     * @return trả về true nếu số dư đủ
     */
    public boolean hasSufficientFunds(BigDecimal currentBalance, BigDecimal amount) {
        return currentBalance.compareTo(amount) > 0;
    }
}