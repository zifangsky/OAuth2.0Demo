package cn.zifangsky.enums;

import java.util.concurrent.TimeUnit;

/**
 * 过期时间相关枚举
 *
 * @author zifangsky
 * @date 2018/8/18
 * @since 1.0.0
 */
public enum ExpireEnum {
    //Authorization Code的有效期为10分钟
    AUTHORIZATION_CODE(10L, TimeUnit.MINUTES),
    //Access Token的有效期为30天
    ACCESS_TOKEN(30L, TimeUnit.DAYS),
    //Refresh Token的有效期为365天
    REFRESH_TOKEN(365L,TimeUnit.DAYS)
    ;

    /**
     * 过期时间
     */
    private Long time;
    /**
     * 时间单位
     */
    private TimeUnit timeUnit;

    ExpireEnum(Long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public Long getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
