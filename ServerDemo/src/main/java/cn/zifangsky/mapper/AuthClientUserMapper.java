package cn.zifangsky.mapper;

import cn.zifangsky.model.AuthClientUser;
import org.apache.ibatis.annotations.Param;

public interface AuthClientUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthClientUser record);

    int insertSelective(AuthClientUser record);

    AuthClientUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthClientUser record);

    int updateByPrimaryKey(AuthClientUser record);

    /**
     * 根据 clientId、userId、scopeId查询用户给某个接入客户端的授权信息
     * @author zifangsky
     * @date 2018/8/17 16:05
     * @since 1.0.0
     * @param clientId 接入的客户端ID
     * @param userId 用户ID
     * @return cn.zifangsky.model.AuthClientUser
     */
    AuthClientUser selectByClientId(@Param("clientId") Integer clientId, @Param("userId") Integer userId, @Param("scopeId") Integer scopeId);
}