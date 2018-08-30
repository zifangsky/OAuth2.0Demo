package cn.zifangsky.enums;

/**
 * 错误码相关枚举类
 *
 * @author zifangsky
 * @date 2018/8/17
 * @since 1.0.0
 */
public enum  ErrorCodeEnum {
    INVALID_REQUEST("invalid_request","请求缺少某个必需参数，包含一个不支持的参数或参数值，或者格式不正确。")
    ,INVALID_CLIENT("invalid_client","请求的client_id或client_secret参数无效。")
    ,INVALID_GRANT("invalid_grant","请求的Authorization Code、Access Token、Refresh Token等信息是无效的。")
    ,UNSUPPORTED_GRANT_TYPE("unsupported_grant_type","不支持的grant_type。")
    ,INVALID_SCOPE("invalid_scope","请求的scope参数是无效的、未知的、格式不正确的，或所请求的权限范围超过了数据拥有者所授予的权限范围。")
    ,EXPIRED_TOKEN("expired_token","请求的Access Token或Refresh Token已过期。")
    ,REDIRECT_URI_MISMATCH("redirect_uri_mismatch","请求的redirect_uri所在的域名与开发者注册应用时所填写的域名不匹配。")
    ,INVALID_REDIRECT_URI("invalid_redirect_uri","请求的回调URL不在白名单中。")
    ,UNKNOWN_ERROR("unknown_error","程序发生未知异常，请联系管理员解决。");

    /**
     * 错误码
     */
    private String error;
    /**
     * 错误描述信息
     */
    private String errorDescription;

    ErrorCodeEnum(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
