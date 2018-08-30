package cn.zifangsky.mapper;

import cn.zifangsky.model.SsoWhiteList;
import org.apache.ibatis.annotations.Param;

public interface SsoWhiteListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsoWhiteList record);

    int insertSelective(SsoWhiteList record);

    SsoWhiteList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsoWhiteList record);

    int updateByPrimaryKey(SsoWhiteList record);

    /**
     * 根据URL查询记录
     * @author zifangsky
     * @date 2018/8/30 16:36
     * @since 1.0.0
     * @param domain 回调URL
     * @return cn.zifangsky.model.SsoWhiteList
     */
    SsoWhiteList selectByDomain(@Param("domain") String domain);
}