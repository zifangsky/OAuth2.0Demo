package cn.zifangsky.enums;

/**
 * 授权方式
 *
 * @author zifangsky
 * @date 2018/8/18
 * @since 1.0.0
 */
public enum GrantTypeEnum {
    //授权码模式
    AUTHORIZATION_CODE("authorization_code");

    private String type;

    GrantTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
