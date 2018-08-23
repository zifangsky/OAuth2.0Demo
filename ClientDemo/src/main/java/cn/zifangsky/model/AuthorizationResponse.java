package cn.zifangsky.model;

/**
 * Authorization返回信息
 *
 * @author zifangsky
 * @date 2018/7/25
 * @since 1.0.0
 */
public class AuthorizationResponse {

    /**
     * 要获取的Access Token（30天的有效期）
     */
    private String access_token;

    /**
     * 用于刷新Access Token 的 Refresh Token（10年的有效期）
     */
    private String refresh_token;

    /**
     * Access Token最终的访问范围
     */
    private String scope;

    /**
     * Access Token的有效期，以秒为单位（30天的有效期）
     */
    private Long expires_in;

    /**
     * 基于http调用Open API时所需要的Session Key，其有效期与Access Token一致
     */
    private String session_key;

    /**
     * 基于http调用Open API时计算参数签名用的签名密钥
     */
    private String session_secret;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 错误描述
     */
    private String error_description;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getSession_secret() {
        return session_secret;
    }

    public void setSession_secret(String session_secret) {
        this.session_secret = session_secret;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    @Override
    public String toString() {
        return "AuthorizationResponse{" +
                "access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", scope='" + scope + '\'' +
                ", expires_in=" + expires_in +
                ", session_key='" + session_key + '\'' +
                ", session_secret='" + session_secret + '\'' +
                ", error='" + error + '\'' +
                ", error_description='" + error_description + '\'' +
                '}';
    }
}
