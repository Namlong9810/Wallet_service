package com.hottea.ewallet.wallet.entity;

import com.hottea.ewallet.wallet.repository.ConfigurationRepository;
import com.hottea.ewallet.wallet.entity.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * DbInitializer là lớp thực hiện thêm mới dữ liệu của bảng Config vào cơ sở dữ liệu
 * @author namhm
 * @version 1.0
 * @since 25-11-2024
 */

@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final ConfigurationRepository configurationRepository;

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra xem cơ sở dữ liệu có tồn tại hay không, tránh thêm lặp dữ liệu mẫu vào cơ sở dữ liệu
        if(configurationRepository.count() == 0){
            System.out.println("Inserting sample data into the database...");

            // Dữ liệu mẫu cho bảng Configuration
            List<Configuration> configurations = new ArrayList<>();
            configurations.add(new Configuration("STATUS", "ACTIVE", 0, true));
            configurations.add(new Configuration("STATUS", "INACTIVE", 1, true));
            configurations.add(new Configuration("STATUS", "BLOCKED", 2, true));
            configurations.add(new Configuration("CURRENCY", "VND", 0, true));
            configurations.add(new Configuration("CURRENCY", "USD", 1, true));

            //Lưu dữ liệu vào cơ sở dữ liệu
            configurationRepository.saveAll(configurations);

            System.out.println("Sample data inserted into the database.");
        }
    }
}
