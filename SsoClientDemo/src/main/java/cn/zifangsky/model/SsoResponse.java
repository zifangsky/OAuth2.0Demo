package cn.zifangsky.model;

import cn.zifangsky.model.bo.UserBo;

/**
 * Authorization返回信息
 *
 * @author zifangsky
 * @date 2018/7/25
 * @since 1.0.0
 */
public class SsoResponse {

    /**
     * 要获取的Access Token（30天的有效期）
     */
    private String access_token;

    /**
     * 用于刷新Access Token 的 Refresh Token（10年的有效期）
     */
    private String refresh_token;

    /**
     * Access Token的有效期，以秒为单位（30天的有效期）
     */
    private Long expires_in;

    /**
     * 用户信息
     */
    private UserBo user_info;

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

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public UserBo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserBo user_info) {
        this.user_info = user_info;
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
        return "SsoResponse{" +
                "access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", expires_in=" + expires_in +
                ", user_info=" + user_info +
                ", error='" + error + '\'' +
                ", error_description='" + error_description + '\'' +
                '}';
    }
}
