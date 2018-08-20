package cn.zifangsky.mapper;

import cn.zifangsky.model.AuthClientDetails;
import org.apache.ibatis.annotations.Param;

public interface AuthClientDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthClientDetails record);

    int insertSelective(AuthClientDetails record);

    AuthClientDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthClientDetails record);

    int updateByPrimaryKey(AuthClientDetails record);

    /**
     * 通过clientId查询接入的客户端详情
     * @author zifangsky
     * @date 2018/8/3 10:31
     * @since 1.0.0
     * @param clientId clientId
     * @return cn.zifangsky.model.AuthClientDetails
     */
    AuthClientDetails selectByClientId(@Param("clientId") String clientId);
}