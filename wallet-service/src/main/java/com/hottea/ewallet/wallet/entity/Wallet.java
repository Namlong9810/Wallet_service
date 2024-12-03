package com.hottea.ewallet.wallet.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;

/**
 * Định nghĩa đối tượng wallet
 * @author namhm
 * @version 1.0
 * @since 23-11-2024
 */


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends BaseEntity{
    @Id
    @Column(name = "wallet_id")
    private String walletId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency")
    private Integer currency;

    @Column(name = "status")
    private Integer status;
}
