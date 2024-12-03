package com.hottea.ewallet.wallet.client;


import com.hottea.ewallet.wallet.dto.response.user.UserClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * interface UserServiceClient dùng đẻ khai báo giao tiếp với user-service
 * @author  namhm
 * @since 25-11-2024
 */

@FeignClient(name = "user-service", url = "http://localhost:8082/api/v1/users")
public interface UserServiceClient {
    @PostMapping("/create")
    void createUser(@RequestBody UserClient userClient);

    @GetMapping("/{id}")
    UserClient getUserById(@PathVariable UUID id);

    @PutMapping("/update")
    void updateUser(@RequestBody UserClient userClient);

}
