package com.hong.pay.aliPay

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import com.alipay.sdk.app.PayTask
import com.hong.PayReqData
import com.hong.pay.PayImpl
import com.hong.pay.PayPreCheck
import java.lang.ref.WeakReference


/**
 *
 * @Description:
 * @Author:         slh
 * @CreateDate:     2021/9/1 11:24
 * @UpdateUser:     更新者：
 * @UpdateDate:     2021/9/1 11:24
 * @UpdateRemark:   更新说明：
 * @Version:        2.2.4
 */
class AliPayManager : PayImpl() {
    private val handler = Handler(
        Looper.getMainLooper()
    ) {
        when (it.what) {
            SDK_PAY_FLAG -> {
                val payResult = PayResult(it.obj as Map<String, String>)
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(payResult.resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    payCallBack?.paySuccess()
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    payCallBack?.paFail(payResult.resultStatus)
                }
                return@Handler true
            }
        }
        return@Handler false
    }

    override fun pay(ctx: Activity, reqData: PayReqData): PayPreCheck {
        val contextWrapper = WeakReference(ctx)
        if (reqData.aliOrderInfo.isEmpty()) {
            return PayPreCheck(500, "订单号为空")
        }
        Thread {
            val aliPay = PayTask(contextWrapper.get() ?: ctx)
            val result: Map<String, String> = aliPay.payV2(reqData.aliOrderInfo, true)
            val msg = Message()
            msg.what = SDK_PAY_FLAG
            msg.obj = result
            handler.sendMessage(msg)
        }.start()
        return PayPreCheck(200, "")
    }

    companion object {
        const val SDK_PAY_FLAG = 1
    }
}