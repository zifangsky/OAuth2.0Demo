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

    AuthRefreshToken selectByTokenId(@Param("tokenId") Integer tokenId);
}