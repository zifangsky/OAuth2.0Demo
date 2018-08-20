package cn.zifangsky.mapper;

import cn.zifangsky.model.AuthScope;
import org.apache.ibatis.annotations.Param;

public interface AuthScopeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthScope record);

    int insertSelective(AuthScope record);

    AuthScope selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthScope record);

    int updateByPrimaryKey(AuthScope record);

    /**
     * 通过scopeName查询
     * @author zifangsky
     * @date 2018/8/18 11:48
     * @since 1.0.0
     * @param scopeName 可被访问的用户的权限范围，比如：basic、super
     * @return cn.zifangsky.model.AuthScope
     */
    AuthScope selectByScopeName(@Param("scopeName") String scopeName);
}