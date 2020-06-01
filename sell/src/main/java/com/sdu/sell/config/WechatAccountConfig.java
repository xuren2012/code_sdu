package com.sdu.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/4/28
 */

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    //公众平台
    private String mpAppId;

    private String mpAppSecret;

    //开放平台
    private String openAppId;

    private String openAppSecret;

    //商户编号
    private String mchId;

    //商户密钥
    private String mchKey;

    //商户证书路径
    private String keyPath;

    //微信支付异步通知地址
    private String notifyUrl;

    private Map<String,String> templateId;

}
