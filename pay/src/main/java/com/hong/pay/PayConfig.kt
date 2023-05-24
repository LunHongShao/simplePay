package com.hong.pay


/**
 *
 * @Description:
 * @Author:         slh
 * @CreateDate:     2021/9/1 11:22
 * @UpdateUser:     更新者：
 * @UpdateDate:     2021/9/1 11:22
 * @UpdateRemark:   更新说明：
 * @Version:        2.2.4
 */
object PayConfig {
//    支付宝

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    const val APPID = ""

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    const val PID = ""

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    const val TARGET_ID = ""

    /**
     * pkcs8 格式的商户私钥。
     *
     * 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * RSA2_PRIVATE。
     *
     * 建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    const val RSA2_PRIVATE = ""
}