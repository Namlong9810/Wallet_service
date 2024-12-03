package com.hottea.ewallet.wallet.config.message;

/**
 * Lớp giao diện mã lỗi cho wallet
 *
 * @author namhm
 * @version 1.0
 * @since 25-11-2024
 */
public class LabelKey {
    public static final String ERROR_INTERNAL_SERVER = "error.internal-server";
    public static final String ERROR_BAB_REQUEST = "error.bad-request";
    public static final String SUCCESS_DEFAULT = "success.default";
    public static final String SUCCESS_CREATE = "success.create-success";

//Lỗi khi kiểm tra ví
    //Lỗi không tim thấy ví
    //Lỗi không tìm thấy id người dùng
    //Lỗi không tìm được id ví của người dùng(Invalid wallet Id)
    //Lỗi số dư không khả dụng (Insufficient funds)
    //Lỗi cập nhật không tìm thấy(Update not found)

    public static final String ERROR_BALANCE_IS_EMPTY = "error.balance-is-empty";
    public static final String ERROR_BALANCE_IS_NEGATIVE = "error.balance-is-negative";
    public static final String ERROR_CURRENCY_IS_INVALID = "error.currency-is-invalid";
    public static final String ERROR_CURRENCY_IS_EMPTY = "error.currency-is-empty";
    public static final String ERROR_USER_ID_IS_EMPTY = "error.user-id-is-empty";
    public static final String ERROR_WALLET_STATUS_IS_INVALID = "error.status-is-invalid";
    public static final String ERROR_WALLET_STATUS_IS_EMPTY = "error.amount-is-empty";
}
