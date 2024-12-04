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
@Builder
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

    public Wallet(String walletId, UUID userId, BigDecimal balance, Integer currency, Integer status, Instant createdAt, Instant updatedAt) {
        super(createdAt, updatedAt);
        this.walletId = walletId;
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
    }

    public Wallet(String walletId, BigDecimal balance, Integer currency, Integer status, Instant createdAt, Instant updatedAt) {
        super(createdAt, updatedAt);
        this.walletId = walletId;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
    }
}
