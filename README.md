# simplePay（支付宝支付，微信支付）
a library for pay of android (include aliPay&amp;wechatPay)

一个封装了支付宝和和微信支付的工具库，主要为了方便无脑接入微信支付和支付宝支付，屏蔽了繁杂的参数细节和意义，约束了支付行为，只需按照库方法一步一步调用即可完成！

# 特别说明：

为了方便在主工程中集成，避免造成依赖冲突，需要自行在主工程中添加支付宝和微信的依赖：

```groovy
implementation 'com.tencent.mm.opensdk:wechat-sdk-android-without-mta:6.8.0'
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
