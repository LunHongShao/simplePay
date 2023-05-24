package com.hong.pay

import android.app.Activity
import com.hong.PayReqData
import com.hong.pay.aliPay.PayCallBack


/**
 * @Description:
 * @Author:         slh
 * @CreateDate:     2021/9/1 11:26
 * @UpdateUser:     更新者：
 * @UpdateDate:     2021/9/1 11:26
 * @UpdateRemark:   更新说明：
 * @Version:        2.2.4
 */
abstract class PayImpl {
    internal var payCallBack: PayCallBack? = null
    abstract fun pay(ctx: Activity, reqData: PayReqData): PayPreCheck
    fun setPayCallBack(payCallBack: PayCallBack) {
        this.payCallBack = payCallBack
    }
}