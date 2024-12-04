package com.hottea.ewallet.wallet.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hottea.ewallet.wallet.entity.Configuration;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    /**
     * Find a configuration by groupConfig and value.
     *
     * @param groupConfig   the configuration key (e.g., "STATUS", "CURRENCY").
     * @param key the configuration value (e.g., "ACTIVE", "VND").
     * @return an Optional containing the Configuration if found.
     */
    Optional<Configuration> findByGroupConfigAndKey(String groupConfig, String key);

    /**
     * Find a configuration by groupConfig and value.
     *
     * @param groupConfig   the configuration key (e.g., "STATUS", "CURRENCY").
     * @param value the configuration value (e.g., "ACTIVE-0", "VND-0").
     * @return an Optional containing the Configuration if found.
     */
    Optional<Configuration> findByGroupConfigAndValue(String groupConfig, Integer value);
}
