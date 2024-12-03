package com.hottea.ewallet.wallet.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UserClient {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String address;
    private LocalDateTime creationTimestamp;
    private LocalDateTime updateTimestamp;
}
