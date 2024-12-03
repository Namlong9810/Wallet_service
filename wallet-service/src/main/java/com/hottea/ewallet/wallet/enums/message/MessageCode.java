package com.hottea.ewallet.wallet.enums.message;

import com.hottea.ewallet.wallet.config.message.LabelKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum các mã lỗi
 * @author namhm
 * @version 1.0
 * @since 25-11-2024
 */

@Getter
@AllArgsConstructor
public enum MessageCode{
    // Server internal
    MSG1001(LabelKey.ERROR_INTERNAL_SERVER),

    // Bad request
    MSG1002(LabelKey.ERROR_BAB_REQUEST),

    // Wallet error
    MSG2003(LabelKey.ERROR_BALANCE_IS_EMPTY),

    MSG2004(LabelKey.ERROR_BALANCE_IS_NEGATIVE),

    MSG2005(LabelKey.ERROR_CURRENCY_IS_EMPTY),

    MSG2006(LabelKey.ERROR_CURRENCY_IS_INVALID),

    MSG2007(LabelKey.ERROR_USER_ID_IS_EMPTY),

    MSG2008(LabelKey.ERROR_USER_ID_IS_EMPTY),

    MSG2009(LabelKey.ERROR_WALLET_STATUS_IS_INVALID),

    MSG2010(LabelKey.ERROR_WALLET_STATUS_IS_EMPTY),

    // Success
    MSG2000(LabelKey.SUCCESS_DEFAULT),

    // Add new wallet successfully
    MSG2001(LabelKey.SUCCESS_CREATE),


    ;

    private String key;
}
