package com.hong.pay.aliPay


/**
 *
 * @Description:
 * @Author:         slh
 * @CreateDate:     2021/9/1 15:54
 * @UpdateUser:     更新者：
 * @UpdateDate:     2021/9/1 15:54
 * @UpdateRemark:   更新说明：
 * @Version:        2.2.4
 */
interface PayCallBack {
    //支付成功回调
    fun paySuccess()

    //支付失败
    /**
     * 返回码	含义
    9000	订单支付成功
    8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    4000	订单支付失败
    5000	重复请求
    6001	用户中途取消
    6002	网络连接出错
    6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    其它	其它支付错误
     *
     * @param errorCode
     */
    fun paFail(errorCode: String)
}