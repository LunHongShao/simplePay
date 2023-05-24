package com.hong.simplepay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hong.PayManager
import com.hong.PayReqData
import com.hong.pay.aliPay.PayCallBack

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PayManager.initManager(this,"your wechat appId")
    }

    fun aliPayClick(view: View) {
        //todo 实际得业务订单数据
        val reqData = PayReqData()
        val handler = PayManager.getPayHandler(PayManager.ALI_PAY)
        //只有支付宝需要设置回调监听支付结果，微信会在
        /**
         * [com.hong.simplepay.wxapi.WXPayEntryActivity]中回调
         */
        handler?.setPayCallBack(object : PayCallBack {
            override fun paySuccess() {
                //TODO("Not yet implemented")
            }

            override fun paFail(errorCode: String) {
                //TODO("Not yet implemented")
            }

        })
        handler?.pay(this, reqData)
    }

    fun weichatPay(view: View) {
        //todo 实际得业务订单数据
        val reqData = PayReqData()
        val handler = PayManager.getPayHandler(PayManager.WE_CHAT_PAY)
        //微信会在
        /**
         * [com.hong.simplepay.wxapi.WXPayEntryActivity]中回调支付结果
         */
        handler?.pay(this, reqData)
    }
}
