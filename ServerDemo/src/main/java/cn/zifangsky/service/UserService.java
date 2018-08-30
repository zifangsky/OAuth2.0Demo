package cn.zifangsky.service;

import cn.zifangsky.model.User;
import cn.zifangsky.model.bo.UserBo;

import java.util.Map;

/**
 * 用于相关Service
 * @author zifangsky
 * @date 2018/7/27
 * @since 1.0.0
 */
public interface UserService {
    /**
     * 通过用户ID查询用户信息
     * @author zifangsky
     * @date 2018/8/23 15:07
     * @since 1.0.0
     * @param userId 用户ID
     * @return cn.zifangsky.model.User
     */
    User selectByUserId(Integer userId);

    /**
     * 注册
     * @author zifangsky
     * @date 2018/7/27 10:48
     * @since 1.0.0
     * @param user 用户详情
     * @return boolean
     */
    boolean register(User user);

    /**
     * 登录校验
     * @author zifangsky
     * @date 2018/7/27 10:48
     * @since 1.0.0
     * @param username 用户名
     * @param password 密码
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String,Object> checkLogin(String username, String password);

    /**
     * 给用户添加角色信息
     * @author zifangsky
     * @date 2018/8/10 17:30
     * @since 1.0.0
     * @param userId 用户ID
     * @param roleName 角色名
     */
    void addUserRole(Integer userId, String roleName);

    /**
     * 通过scope查询不同程度的用户信息
     * @author zifangsky
     * @date 2018/8/22 20:04
     * @since 1.0.0
     * @param userId 用户ID
     * @param scope scope
     * @return cn.zifangsky.model.User
     */
    User selectUserInfoByScope(Integer userId, String scope);

    /**
     * 通过用户ID查询用户所属角色等信息
     * @author zifangsky
     * @date 2018/8/30 15:22
     * @since 1.0.0
     * @param userId 用户ID
     * @return cn.zifangsky.model.bo.UserBo
     */
    UserBo selectUserBoByUserId(Integer userId);
}
