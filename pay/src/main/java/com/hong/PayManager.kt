package com.hong

import android.content.Context
import android.util.SparseArray
import com.hong.pay.PayImpl
import com.hong.pay.aliPay.AliPayManager
import com.hong.pay.weichatPay.WeChatPayManager


/**
 *
 * @Description:
 * @Author:         slh
 * @CreateDate:     2021/9/1 16:00
 * @UpdateUser:     更新者：
 * @UpdateDate:     2021/9/1 16:00
 * @UpdateRemark:   更新说明：
 * @Version:        2.2.4
 */
object PayManager {
    private val pay = SparseArray<PayImpl>()
    const val ALI_PAY = 1
    const val WE_CHAT_PAY = 2

    fun initManager(ctx: Context, weChatAppId: String) {
        pay.put(ALI_PAY, AliPayManager())
        pay.put(WE_CHAT_PAY, WeChatPayManager(ctx, weChatAppId))
    }

    /**
     * 清除引用
     *
     */
    fun clearAllReference() {
        for (index in 0 until pay.size()) {
            val payInstance = pay.valueAt(index)
            payInstance.payCallBack = null
        }
    }

    fun getPayHandler(payType: Int): PayImpl? {
        return pay.get(payType)
    }
}