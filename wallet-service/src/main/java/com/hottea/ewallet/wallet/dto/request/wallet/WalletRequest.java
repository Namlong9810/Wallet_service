package com.hottea.ewallet.wallet.dto.request.wallet;





import lombok.*;

import java.math.BigDecimal;

/**
 * WalletRequest là class yêu cầu transaction để cập nhật số dư của ví
 * @author namhm
 * @version 1.0
 * @since 25-11-2024
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {
    private String walletId;
    private BigDecimal amount;
}
