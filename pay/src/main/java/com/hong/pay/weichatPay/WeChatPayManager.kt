package com.hong.pay.weichatPay

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.hong.PayReqData
import com.hong.pay.PayImpl
import com.hong.pay.PayPreCheck


/**
 *
 * @Description:
 * @Author:         slh
 * @CreateDate:     2021/9/1 14:15
 * @UpdateUser:     更新者：
 * @UpdateDate:     2021/9/1 14:15
 * @UpdateRemark:   更新说明：
 * @Version:        2.2.4
 */
class WeChatPayManager(val ctx: Context, val appId: String) : PayImpl() {
    private val wxApi: IWXAPI by lazy { WXAPIFactory.createWXAPI(ctx, null) }

    init {
        wxApi.registerApp(appId)
        ContextCompat.registerReceiver(ctx, object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                wxApi.registerApp(appId)
            }
        }, IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP), ContextCompat.RECEIVER_EXPORTED)

    }

    override fun pay(ctx: Activity, reqData: PayReqData): PayPreCheck {
        val payReq = PayReq()
        payReq.apply {
            appId = reqData.appid
            partnerId = reqData.partnerid
            prepayId = reqData.prepayid
            nonceStr = reqData.noncestr
            timeStamp = reqData.timestamp
            packageValue = reqData.`package`
            sign = reqData.sign
        }
        if (!payReq.checkArgs()) {
            return PayPreCheck(500, "参数不合法")
        }
        wxApi.sendReq(payReq)
        return PayPreCheck(200, "")
    }

//    companion object {
//        private const val TAG = "WechatPay"
//    }
}