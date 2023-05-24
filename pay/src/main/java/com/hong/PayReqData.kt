package com.hong


/**
 *
 * @Description:
 * @Author:         slh
 * @CreateDate:     2021/9/1 13:48
 * @UpdateUser:     更新者：
 * @UpdateDate:     2021/9/1 13:48
 * @UpdateRemark:   更新说明：
 * @Version:        2.2.4
 */
/**
 *********************微信支付部分**************************************
 * @property appid  微信开放平台审核通过的应用APPID（请登录open.weixin.qq.com查看，注意与公众号的APPID不同）
 * @property partnerid 微信支付分配的商户号
 * @property prepayid  微信返回的支付交易会话ID
 * @property noncestr 随机字符串，不长于32位。推荐随机数生成算法
 * @property timestamp 时间戳，请见接口规则-参数规定
 * @property sign 签名，详见签名生成算法注意：签名方式一定要与统一下单接口使用的一致
 * @property package 暂填写固定值Sign=WXPay
 *  ***************************************支付宝部分********************
 * @property aliOrderInfo 支付宝的订单信息
 */
data class PayReqData(
    val appid: String = "",
    val partnerid: String = "",
    val prepayid: String = "",
    val noncestr: String = "",
    val timestamp: String = "",
    val sign: String = "",
    val `package`: String = "Sign=WXPay",
    val aliOrderInfo: String = ""
)