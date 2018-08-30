package cn.zifangsky.service.impl;

import cn.zifangsky.enums.ScopeEnum;
import cn.zifangsky.mapper.RoleMapper;
import cn.zifangsky.mapper.UserMapper;
import cn.zifangsky.mapper.UserRoleMapper;
import cn.zifangsky.model.Role;
import cn.zifangsky.model.User;
import cn.zifangsky.model.UserRole;
import cn.zifangsky.model.bo.RoleBo;
import cn.zifangsky.model.bo.UserBo;
import cn.zifangsky.service.UserService;
import cn.zifangsky.utils.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zifangsky
 * @date 2018/8/3
 * @since 1.0.0
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User selectByUserId(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean register(User user) {
        if(StringUtils.isNoneBlank(user.getUsername()) && StringUtils.isNoneBlank(user.getPassword())){
            Date current = new Date();

            //密码加密存储
            user.setPassword(EncryptUtils.sha256Crypt(user.getPassword(),null));
            user.setCreateTime(current);
            user.setUpdateTime(current);
            user.setStatus(1);

            userMapper.insertSelective(user);
            LOGGER.info("用户注册 --> " + user);

            return true;
        }

        return false;
    }

    @Override
    public Map<String,Object> checkLogin(String username, String password) {
        //返回信息
        Map<String,Object> result = new HashMap<>(2);
        LOGGER.info(MessageFormat.format("用户登录 --> username:{0},password:{1}",username,password));

        User correctUser = userMapper.selectByUsername(username);
        result.put("result", EncryptUtils.checkSha256Crypt(password, correctUser.getPassword()));
        result.put("user", correctUser);

        return result;
    }

    @Override
    public void addUserRole(Integer userId, String roleName) {
        if(userId != null && StringUtils.isNoneBlank(roleName)){
            //1. 查询角色ID
            Role role = roleMapper.selectByRoleName(roleName);

            if(role != null){
                UserRole savedUserRole = userRoleMapper.selectByUserIdRoleId(userId, role.getId());

                if(savedUserRole == null){
                    //2. 给用户添加角色信息
                    UserRole userRole = new UserRole(userId, role.getId());
                    userRoleMapper.insert(userRole);
                }
            }
        }
    }

    @Override
    public User selectUserInfoByScope(Integer userId, String scope) {
        User user = userMapper.selectByPrimaryKey(userId);

        //如果是基础权限，则部分信息不返回
        if(ScopeEnum.BASIC.getCode().equalsIgnoreCase(scope)){
            user.setPassword(null);
            user.setCreateTime(null);
            user.setUpdateTime(null);
            user.setStatus(null);
        }

        return user;
    }

    @Override
    public UserBo selectUserBoByUserId(Integer userId) {
        UserBo result = new UserBo();
        User user = userMapper.selectByPrimaryKey(userId);


        if(user != null){
            BeanUtils.copyProperties(user, result);

            List<UserRole> userRoleList = userRoleMapper.selectByUserId(userId);

            if(userRoleList != null && userRoleList.size() > 0){
                //查询用户对应的所有角色
                Set<RoleBo> roles = new HashSet<>();

                for(UserRole userRole : userRoleList){
                    Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
                    RoleBo roleBo = new RoleBo();
                    BeanUtils.copyProperties(role, roleBo);
                    //可访问的功能权限相关信息在这个demo中就省略了
                    roles.add(roleBo);
                }

                result.setRoles(roles);
            }

        }

        return result;
    }
}
