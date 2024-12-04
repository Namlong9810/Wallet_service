package com.hottea.ewallet.wallet.dto.response.wallet;

import com.hottea.ewallet.wallet.entity.BaseEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * WalletResponse trả về thông tin ví của người dùng
 *
 * @author namhm
 * @version 1.0
 * @since 24-11-2024
 */



@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Data
@Builder
public class WalletResponse {
    private String walletId;
    private BigDecimal balance;
    private String currency;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}
