package cn.zifangsky.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * SSO登录之后的Token可以用于什么渠道
 *
 * @author zifangsky
 * @date 2018/8/30
 * @since 1.0.0
 */
public enum ChannelEnum {
    TEST_CLIENT1("TEST_CLIENT1","测试客户端一"),APP1("APP1","app1"),APP2("APP2","app2");

    private String code;
    private String description;

    ChannelEnum(String code, String description) {
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

    public static ChannelEnum fromCode(String code){
        if(StringUtils.isNoneBlank(code)){
            for(ChannelEnum channel : ChannelEnum.values()){
                if(channel.getCode().equals(code)){
                    return channel;
                }
            }
        }

        return null;
    }
}
