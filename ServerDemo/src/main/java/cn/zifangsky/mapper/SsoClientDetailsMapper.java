package cn.zifangsky.mapper;

import cn.zifangsky.model.SsoClientDetails;
import org.apache.ibatis.annotations.Param;

public interface SsoClientDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsoClientDetails record);

    int insertSelective(SsoClientDetails record);

    SsoClientDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsoClientDetails record);

    int updateByPrimaryKey(SsoClientDetails record);

    /**
     * 根据URL查询记录
     * @author zifangsky
     * @date 2018/8/30 16:36
     * @since 1.0.0
     * @param redirectUrl 回调URL
     * @return cn.zifangsky.model.SsoClientDetails
     */
    SsoClientDetails selectByRedirectUrl(@Param("redirectUrl") String redirectUrl);
}