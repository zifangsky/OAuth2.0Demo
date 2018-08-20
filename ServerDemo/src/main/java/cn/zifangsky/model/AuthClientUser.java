package cn.zifangsky.model;

public class AuthClientUser {
    private Integer id;

    private Integer authClientId;

    private Integer userId;

    private Integer authScopeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthClientId() {
        return authClientId;
    }

    public void setAuthClientId(Integer authClientId) {
        this.authClientId = authClientId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuthScopeId() {
        return authScopeId;
    }

    public void setAuthScopeId(Integer authScopeId) {
        this.authScopeId = authScopeId;
    }
}