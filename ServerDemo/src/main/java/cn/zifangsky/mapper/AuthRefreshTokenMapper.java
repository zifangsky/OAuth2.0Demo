package cn.zifangsky.mapper;

import cn.zifangsky.model.AuthRefreshToken;
import org.apache.ibatis.annotations.Param;

public interface AuthRefreshTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRefreshToken authRefreshToken);

    int insertSelective(AuthRefreshToken authRefreshToken);

    AuthRefreshToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthRefreshToken authRefreshToken);

    int updateByPrimaryKey(AuthRefreshToken authRefreshToken);

    /**
     * 通过tokenId查询记录
     * @author zifangsky
     * @date 2018/8/20 17:27
     * @since 1.0.0
     * @param tokenId tokenId
     * @return cn.zifangsky.model.AuthRefreshToken
     */
    AuthRefreshToken selectByTokenId(@Param("tokenId") Integer tokenId);

    /**
     * 通过Refresh Token查询记录
     * @author zifangsky
     * @date 2018/8/22 11:35
     * @since 1.0.0
     * @param refreshToken Refresh Token
     * @return cn.zifangsky.model.AuthRefreshToken
     */
    AuthRefreshToken selectByRefreshToken(@Param("refreshToken") String refreshToken);
}