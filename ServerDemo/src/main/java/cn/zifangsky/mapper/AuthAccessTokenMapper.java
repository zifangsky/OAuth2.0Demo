package cn.zifangsky.mapper;

import cn.zifangsky.model.AuthAccessToken;
import org.apache.ibatis.annotations.Param;

public interface AuthAccessTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthAccessToken authAccessToken);

    int insertSelective(AuthAccessToken authAccessToken);

    AuthAccessToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthAccessToken authAccessToken);

    int updateByPrimaryKey(AuthAccessToken authAccessToken);

    /**
     * 通过userId + clientId + scope查询记录
     * @author zifangsky
     * @date 2018/8/20 11:08
     * @since 1.0.0
     * @param userId 用户ID
     * @param clientId 接入的客户端ID
     * @param scope scope
     * @return cn.zifangsky.model.AuthAccessToken
     */
    AuthAccessToken selectByUserIdClientIdScope(@Param("userId") Integer userId, @Param("clientId") Integer clientId, @Param("scope") String scope);

    /**
     * 通过Access Token查询记录
     * @author zifangsky
     * @date 2018/8/20 14:33
     * @since 1.0.0
     * @param accessToken Access Token
     * @return cn.zifangsky.model.AuthAccessToken
     */
    AuthAccessToken selectByAccessToken(@Param("accessToken") String accessToken);
}