package com.hottea.ewallet.wallet.dto.request.wallet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * WalletStatusRequest là class yêu cầu cập nhật trạng thái của ví 
 * @author namhm
 * @version 1.0
 * @since 25-11-2024
 */

@Getter
@Setter
@ToString
public class WalletStatusRequest {
    private Integer status;
}
