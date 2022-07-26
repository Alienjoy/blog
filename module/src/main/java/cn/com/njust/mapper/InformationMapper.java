package cn.com.njust.mapper;

import cn.com.njust.entity.Information;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname cn.com.njust.mapper
 * @Date 2022/2/11 3:59 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Mapper
public interface InformationMapper {


    /**
     * @param information 需要更新的网站信息
     * @return
     */
    int updateInformation(Information information);

    /**
     * 获得网站信息
     *
     * @return 系统信息
     */
    Information getInformation();
}