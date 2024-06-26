# simplePay（支付宝支付，微信支付）
a library for pay of android (include aliPay&amp;wechatPay)

一个封装了支付宝和和微信支付的工具库，主要为了方便无脑接入微信支付和支付宝支付，屏蔽了繁杂的参数细节和意义，约束了支付行为，只需按照库方法一步一步调用即可完成！

# 特别说明：

为了方便在主工程中集成，避免造成依赖冲突，需要自行在主工程中添加支付宝和微信的依赖：

```groovy
implementation 'com.tencent.mm.opensdk:wechat-sdk-android:6.8.24'
//如果提示oaid冲突就引入这个
//implementation 'com.tencent.mm.opensdk:wechat-sdk-android-without-mta:6.8.0'
implementation 'com.alipay.sdk:alipaysdk-android:15.8.14@aar'
```

# 支付宝开发文档地址

https://opendocs.alipay.com/open/204/105296?pathHash=22ed0058&ref=api

# 微信开发文档地址

https://developers.weixin.qq.com/doc/oplatform/Downloads/Android_Resource.html

https://pay.weixin.qq.com/wiki/doc/apiv3/open/pay/chapter2_5_2.shtml

# 使用方式

gradle:

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.** Add the dependency

```groovy
dependencies {
    implementation'com.github.LunHongShao:simplePay:1.0.1'
	}
```

maven:

**Step 1.** Add the JitPack repository to your build file

```xml
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

**Step 2.** Add the dependency

```xml
	<dependency>
	    <groupId>com.github.LunHongShao</groupId>
	    <artifactId>simplePay</artifactId>
	    <version>1.0.1</version>
	</dependency>
```

# 混淆规则（proguard-rules）

```
#微信
-keep class com.tencent.mm.opensdk.** {
    *;
}

-keep class com.tencent.wxop.** {
    *;
}

-keep class com.tencent.mm.sdk.** {
    *;
}
```

# 微信注意事项

1.首先在项目包根目录下创建wxapi包，并新建WXPayEntryActivity

如果有集成友盟类的社会化分享库则继承WXCallbackActivity

如果没有则实现IWXAPIEventHandler接口并重写

```kotlin
 /** 0	成功	展示成功页面
     * -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
     * 	-2 用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
     *
     * @param p0
     */
    override fun onResp(p0: BaseResp?) {
        if (p0?.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
            //todo 自己的业务逻辑
            finish()
        }
    }
```

2.

```
//注册应用到微信
PayManager.initManager(App.get(), "你的appid")
```

3.通过微信支付参数调起支付

**注意：微信支付回调结果在WXPayEntryActivity中返回！！！**

```kotlin
/**
     * 请求支付
     *
     * @param params
     */
    fun requestPayment(payParams: PayReqData) {
        try {
            payParams?.let {
                val paySdk = if (payParams.aliOrderInfo.isEmpty()) {
                    PayManager.getPayHandler(PayManager.WE_CHAT_PAY)
                } else {
                    PayManager.getPayHandler(PayManager.ALI_PAY)
                }
                paySdk?.setPayCallBack(object : PayCallBack {
                    override fun paySuccess() {
                        // todo 支付宝支付成功
                    }

                    override fun paFail(errorCode: String) {
                        //支付宝支付失败
                    }

                })
                val res=paySdk?.pay(ctx, payParams)//states=200参数预校验通过  500参数异常，具体原因查看errorMsg
            }
        } catch (e: Exception) {
            ToastHelper.show("支付出错啦~${e.message}")
            DataReportHelper.reportError(e)
        }


    }
```

4.清理引用PayManager.clearAllReference()

# 支付宝使用

```kotlin
PayManager.getPayHandler(PayManager.ALI_PAY)?.setPayCallBack(object : PayCallBack {
    override fun paySuccess() {
        //支付宝支付成功
    }

    override fun paFail(errorCode: String) {
        //失败
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
     * 
     */
    }

})
paySdk?.pay(ctx, payParams)
```

# PayReqData字段说明

```kotlin
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
```
