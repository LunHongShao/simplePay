package com.hong.simplepay.wxapi

import android.app.Activity
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler


/**
 *
 * @Description:    微信支付回调
 * @Author:         slh
 * @CreateDate:     2021/9/1 14:52
 * @UpdateUser:     更新者：
 * @UpdateDate:     2021/9/1 14:52
 * @UpdateRemark:   更新说明：
 * @Version:
 */
class WXPayEntryActivity : Activity(), IWXAPIEventHandler {

    override fun onReq(p0: BaseReq?) {
    }


    /** 0	成功	展示成功页面
     * -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
     * 	-2 用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
     *
     * @param p0
     */
    override fun onResp(p0: BaseResp?) {
        if (p0?.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
        }
    }

    companion object {
        private const val TAG = "WXPayEntryActivity"
    }
}