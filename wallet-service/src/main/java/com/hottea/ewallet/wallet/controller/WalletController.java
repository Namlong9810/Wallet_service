package com.hottea.ewallet.wallet.controller;

import com.hottea.ewallet.wallet.dto.response.wallet.WalletResponseToTransaction;
import com.hottea.ewallet.wallet.enums.message.MessageCode;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.hottea.ewallet.wallet.service.WalletService;


import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;

import com.hottea.ewallet.wallet.dto.request.wallet.WalletRequest;
import com.hottea.ewallet.wallet.dto.request.wallet.WalletStatusRequest;
import com.hottea.ewallet.wallet.dto.response.common.ResponseObject;
import com.hottea.ewallet.wallet.dto.response.wallet.WalletResponse;
import com.hottea.ewallet.wallet.entity.Wallet;


/**
 * Controller xử lý các yêu cầu liên quan đến Wallet.
 * @author namhm
 * @version 1.0
 * @since 23-11-2024
 */


@RestController
@RequestMapping("/api/v1/users/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    /**
     *  Tạo mới wallet
     *
     */
    @PostMapping("/create")
    public ResponseObject<Wallet> newWallet(){
        return new ResponseObject<>(MessageCode.MSG2001.getKey(), HttpStatus.CREATED.value(), LocalDateTime.now(),
                walletService.newWallet());
    }

    /**
     * Xem thông tin ví 
     * @param wallet_id
     */
    @GetMapping("/{wallet_id}")
    public ResponseObject<WalletResponse> walletInfor(@PathVariable("wallet_id") String wallet_id){
        return new ResponseObject<>(MessageCode.MSG2000.getKey(), HttpStatus.FOUND.value(), LocalDateTime.now(),
                walletService.walletInfor(wallet_id));
    } 

    /**
     * Cập nhật trạng thái ví
     * @param wallet_id
     */
    @PatchMapping("/{wallet_id}/status")
    public ResponseObject<WalletResponse> walletUpdateStatus(@PathVariable("wallet_id") String wallet_id, @RequestBody WalletStatusRequest request){
        return new ResponseObject<>(MessageCode.MSG2000.getKey(), HttpStatus.OK.value(), LocalDateTime.now(),
                walletService.walletUpdateStatus(wallet_id, request));
    }

    /**
     *  Nhập tiền vào ví
     */
    @PutMapping("/deposit")
    public ResponseObject<WalletResponse> walletDeposit(@RequestBody WalletRequest request){
        return new ResponseObject<>(MessageCode.MSG2000.getKey(), HttpStatus.OK.value(), LocalDateTime.now(),
                walletService.walletDeposit(request));
    }

    /**
     * Rút tiền khỏi ví
     */
    @PutMapping("/withdraw")
    public ResponseObject<WalletResponse> walletWithdraw(@RequestBody WalletRequest request){
        return  new ResponseObject<>(MessageCode.MSG2000.getKey(), HttpStatus.OK.value(), LocalDateTime.now(),
                walletService.walletWithdraw(request));
    }

}