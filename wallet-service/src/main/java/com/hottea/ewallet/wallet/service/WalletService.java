package com.hottea.ewallet.wallet.service;

import com.hottea.ewallet.wallet.dto.request.wallet.WalletRequest;

import com.hottea.ewallet.wallet.dto.request.wallet.WalletStatusRequest;
import com.hottea.ewallet.wallet.dto.response.wallet.WalletResponse;
import com.hottea.ewallet.wallet.dto.response.wallet.WalletResponseToTransaction;
import com.hottea.ewallet.wallet.entity.Wallet;

import java.util.UUID;


/**
 * Interface WalletService định nghĩa các phương thức liên quan định nghĩa Wallet.
 * @author namhm
 * @version 1.0
 * @since 23-11-2024
 *
*/

public interface WalletService {
    /**
     * Phương thức tạo mới ví dựa trên yêu cầu tạo mới 
     *  
     * @param req Yêu cầu tạo mới ví
     * @return  ví mới được tạo
     */    
    Wallet newWallet();

    /**
     * Phương thức xem thông tin của ví 
     * 
     * @param req Yêu cầu xem thông tin ví 
     * @return Thông tin về ví
     */
    WalletResponse walletInfor(String wallet_id);

    /**
     * Phương thức cập nhật trạng thái ví 
     * 
     * @param req Yêu cầu cập nhật trạng thái ví 
     * @return Thông tin ví
     */
    WalletResponse walletUpdateStatus(String wallet_id,WalletStatusRequest req);

    /**
     * Phương thức cập nhật số dư của ví
     *
     * @param req Yêu cầu cập nhật số dư của ví
     * @return Trạng thái trả về cho transaction
     */
    WalletResponseToTransaction walletUpdateBalance(WalletRequest req);
}