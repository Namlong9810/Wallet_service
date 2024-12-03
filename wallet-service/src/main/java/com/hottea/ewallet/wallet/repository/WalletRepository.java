package com.hottea.ewallet.wallet.repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.hottea.ewallet.wallet.dto.response.wallet.WalletResponse;
import com.hottea.ewallet.wallet.entity.Wallet;


import jakarta.transaction.Transactional;

import org.springframework.data.repository.query.Param;


/**
 * Interface định nghĩa các phương thức truy vấn cho thực thể wallet
 * 
 * @author namhm
 * @version 1.0
 * @since 25-11-2024
 */

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT NEW com.hottea.ewallet.wallet.dto.response.wallet.WalletResponse(" +
       "w.balance, w.currency, w.status, w.createdAt, w.updatedAt) " +
       "FROM Wallet w " +
       "WHERE w.walletId = :walletId ")
    Optional<WalletResponse> findWallet(@Param("walletId") String walletId);

    @Transactional
    @Modifying
    @Query("UPDATE Wallet w " +
            "SET w.status = :walletStatus, w.updatedAt = :updatedAt " +
            "WHERE w.walletId = :walletId")
    int updateStatus(@Param("walletId") String walletId, @Param("walletStatus") Integer walletStatus, @Param("updatedAt") Instant updatedAt);

    @Transactional
    @Modifying
    @Query("UPDATE Wallet w " +
            "SET w.balance = w.balance + :amount, w.updatedAt = :updatedAt " +
            "WHERE w.walletId = :walletId")
    int updateBalance(@Param("walletId") String walletId, @Param("amount") BigDecimal amount, @Param("updatedAt") Instant updatedAt);
}


