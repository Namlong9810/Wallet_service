package com.hottea.ewallet.wallet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


/**
 * Định nghĩa đối tượng configuration
 * @author namhm
 * @version 1.0
 * @since 27-11-2024
 */

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "configuration")
public class Configuration extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String groupConfig;// e.g "STATUS" or "CURRENCY"

    @Column(nullable = false)
    private String key; // e.g "ADMIN", "USER", "VND", "USD"

    @Column(nullable = false)
    private Integer value; // e.g "ADMIN-0" , "USER-1", "VND-1", "USD-2"

    @Column(nullable = false)
    private boolean active;

    public Configuration(String groupConfig, String key, Integer value, boolean active) {
        this.groupConfig = groupConfig;
        this.key = key;
        this.value = value;
        this.active = active;
    }
}
