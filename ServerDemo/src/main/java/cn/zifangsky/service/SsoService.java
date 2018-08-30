package cn.zifangsky.service;

import cn.zifangsky.enums.ChannelEnum;
import cn.zifangsky.model.SsoAccessToken;
import cn.zifangsky.model.SsoRefreshToken;
import cn.zifangsky.model.User;

/**
 * SSO单点登录相关Service
 *
 * @author zifangsky
 * @date 2018/8/30
 * @since 1.0.0
 */
public interface SsoService {

    /**
     * 通过主键ID查询记录
     * @author zifangsky
     * @date 2018/8/30 13:11
     * @since 1.0.0
     * @param id ID
     * @return cn.zifangsky.model.SsoAccessToken
     */
    SsoAccessToken selectByAccessId(Integer id);

    /**
     * 通过Access Token查询记录
     * @author zifangsky
     * @date 2018/8/30 13:11
     * @since 1.0.0
     * @param accessToken Access Token
     * @return cn.zifangsky.model.SsoAccessToken
     */
    SsoAccessToken selectByAccessToken(String accessToken);

    /**
     * 通过tokenId查询记录
     * @author zifangsky
     * @date 2018/8/30 13:11
     * @since 1.0.0
     * @param tokenId tokenId
     * @return cn.zifangsky.model.SsoRefreshToken
     */
    SsoRefreshToken selectByTokenId(Integer tokenId);

    /**
     * 通过Refresh Token查询记录
     * @author zifangsky
     * @date 2018/8/30 13:11
     * @since 1.0.0
     * @param refreshToken Refresh Token
     * @return cn.zifangsky.model.SsoRefreshToken
     */
    SsoRefreshToken selectByRefreshToken(String refreshToken);

    /**
     * 生成Access Token
     * @author zifangsky
     * @date 2018/8/30 13:11
     * @since 1.0.0
     * @param user 用户信息
     * @param expiresIn 过期时间
     * @param channel 请求Token的渠道
     * @param requestIP 用户请求的IP
     * @return java.lang.String
     */
    String createAccessToken(User user, Long expiresIn, String requestIP, ChannelEnum channel);


    /**
     * 生成Refresh Token
     * @author zifangsky
     * @date 2018/8/30 13:11
     * @since 1.0.0
     * @param user 用户信息
     * @param ssoAccessToken 生成的Access Token信息
     * @return java.lang.String
     */
    String createRefreshToken(User user, SsoAccessToken ssoAccessToken);

}
