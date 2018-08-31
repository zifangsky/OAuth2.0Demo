package cn.zifangsky.enums;

/**
 * 权限范围
 *
 * @author zifangsky
 * @date 2018/8/3
 * @since 1.0.0
 */
public enum ScopeEnum {
    BASIC("basic","基础权限"),SUPER("super","所有权限");

    private String code;
    private String description;

    ScopeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
