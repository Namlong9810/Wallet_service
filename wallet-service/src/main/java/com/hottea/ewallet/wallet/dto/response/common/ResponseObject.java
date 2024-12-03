package com.hottea.ewallet.wallet.dto.response.common;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Object response định nghĩa dữ liệu trả về client 
 * @author namhm
 * @version 1.0
 * @since 25-11-2024
 * <T> dữ liệu trả về cho người dùng
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject<T> {
    private String message;
    private int restatus;
    private LocalDateTime localDateTime;
    private T data;

    public ResponseObject( String message, int restatus, LocalDateTime localDateTime){
        this.message = message;
        this.restatus = restatus;
        this.localDateTime = localDateTime;
    }
}
