package com.hong.simplepay.wxapi

import android.app.Activity
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler


/**
 *
 * @Description:   微信登录回调入口
 * @Author:         slh
 * @CreateDate:     2020/6/30 19:10
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/6/30 19:10
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class WXEntryActivity : Activity(),IWXAPIEventHandler {

    companion object {
        private const val TAG = "WXEntryActivity"
    }

    override fun onReq(p0: BaseReq?) {
      //  TODO("Not yet implemented")
    }

    override fun onResp(p0: BaseResp?) {
      //  TODO("Not yet implemented")
    }

//    }

}