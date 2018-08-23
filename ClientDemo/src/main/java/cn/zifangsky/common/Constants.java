package cn.zifangsky.common;

/**
 * 公共常量类
 *
 * @author zifangsky
 * @date 2018/7/25
 * @since 1.0.0
 */
public class Constants {
    /**
     * 登录页面的回调地址在session中存储的变量名
     */
    public static final String SESSION_LOGIN_REDIRECT_URL = "LOGIN_REDIRECT_URL";

    /**
     * 请求Authorization Code的随机状态码在session中存储的变量名
     */
    public static final String SESSION_AUTH_CODE_STATUS = "AUTH_CODE_STATUS";

    /**
     * Access Token在session中存储的变量名
     */
    public static final String SESSION_ACCESS_TOKEN = "ACCESS_TOKEN";

    /**
     * 用户信息在session中存储的变量名
     */
    public static final String SESSION_USER = "SESSION_USER";
}
