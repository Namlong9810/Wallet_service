package com.hottea.ewallet.wallet.dto.response.wallet;

import com.hottea.ewallet.wallet.entity.BaseEntity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
public class WalletResponse  extends BaseEntity {
    private BigDecimal balance;
    private Integer currency;
    private Integer status;

    public WalletResponse(BigDecimal balance, Integer currency, Integer status, Instant createdAt, Instant updatedAt) {
        super(createdAt, updatedAt);
        this.balance = balance;
        this.currency = currency;
        this.status = status;

    }
}
