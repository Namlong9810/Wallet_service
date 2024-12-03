package com.hottea.ewallet.wallet.dto.response.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * WalletResponse trả về thông tin ví của người dùng
 *
 * @author namhm
 * @version 1.0
 * @since 25-11-2024
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponseToTransaction {
    private String message;
    private Integer status;
}
