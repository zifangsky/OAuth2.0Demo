package cn.zifangsky.service;

import cn.zifangsky.model.AuthAccessToken;
import cn.zifangsky.model.AuthClientDetails;
import cn.zifangsky.model.AuthRefreshToken;
import cn.zifangsky.model.User;

/**
 * 授权相关Service
 *
 * @author zifangsky
 * @date 2018/8/3
 * @since 1.0.0
 */
public interface AuthorizationService {

    /**
     * 注册需要接入的客户端信息
     * @author zifangsky
     * @date 2018/8/3 16:24
     * @since 1.0.0
     * @param clientDetails 用户传递进来的关键信息
     * @return boolean
     */
    boolean register(AuthClientDetails clientDetails);

    /**
     * 通过id查询客户端信息
     * @author zifangsky
     * @date 2018/8/3 16:45
     * @since 1.0.0
     * @param id client_id
     * @return cn.zifangsky.model.AuthClientDetails
     */
    AuthClientDetails selectClientDetailsById(Integer id);

    /**
     * 通过client_id查询客户端信息
     * @author zifangsky
     * @date 2018/8/3 16:45
     * @since 1.0.0
     * @param clientId client_id
     * @return cn.zifangsky.model.AuthClientDetails
     */
    AuthClientDetails selectClientDetailsByClientId(String clientId);

    /**
     * 通过Access Token查询记录
     * @author zifangsky
     * @date 2018/8/20 14:33
     * @since 1.0.0
     * @param accessToken Access Token
     * @return cn.zifangsky.model.AuthAccessToken
     */
    AuthAccessToken selectByAccessToken(String accessToken);

    /**
     * 通过主键查询记录
     * @author zifangsky
     * @date 2018/8/22 11:40
     * @since 1.0.0
     * @param 
     * @return cn.zifangsky.model.AuthAccessToken
     */
    AuthAccessToken selectByAccessId(Integer id);

    /**
     * 通过Refresh Token查询记录
     * @author zifangsky
     * @date 2018/8/22 11:35
     * @since 1.0.0
     * @param refreshToken Refresh Token
     * @return cn.zifangsky.model.AuthRefreshToken
     */
    AuthRefreshToken selectByRefreshToken(String refreshToken);

    /**
     * 保存哪个用户授权哪个接入的客户端哪种访问范围的权限
     * @author zifangsky
     * @date 2018/8/6 17:46
     * @since 1.0.0
     * @param userId 用户ID
     * @param clientIdStr 接入的客户端client_id
     * @param scopeStr 可被访问的用户的权限范围，比如：basic、super
     */
    boolean saveAuthClientUser(Integer userId, String clientIdStr, String scopeStr);

    /**
     * 根据clientId、scope以及当前时间戳生成AuthorizationCode（有效期为10分钟）
     * @author zifangsky
     * @date 2018/8/18 14:13
     * @since 1.0.0
     * @param clientIdStr 客户端ID
     * @param scopeStr scope
     * @param user 用户信息
     * @return java.lang.String
     */
    String createAuthorizationCode(String clientIdStr, String scopeStr, User user);

    /**
     * 生成Access Token
     * @author zifangsky
     * @date 2018/8/18 17:11
     * @since 1.0.0
     * @param user 用户信息
     * @param savedClientDetails 接入的客户端信息
     * @param grantType 授权方式
     * @param scope 允许访问的用户权限范围
     * @param expiresIn 过期时间
     * @return java.lang.String
     */
    String createAccessToken(User user, AuthClientDetails savedClientDetails, String grantType, String scope, Long expiresIn);


    /**
     * 生成Refresh Token
     * @author zifangsky
     * @date 2018/8/18 17:11
     * @since 1.0.0
     * @param user 用户信息
     * @param authAccessToken 生成的Access Token信息
     * @return java.lang.String
     */
    String createRefreshToken(User user, AuthAccessToken authAccessToken);
}
