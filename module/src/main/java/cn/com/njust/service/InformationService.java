package cn.com.njust.service;

import cn.com.njust.entity.Information;

/**
 * @Classname cn.com.njust.service
 * @Date 2022/2/10 9:59 下午
 * @Created by Zhang Shuheng
 * @Description
 */
public interface InformationService {
    /**
     *
     * @param information 需要更新的网站信息
     * @return
     */
    int updateInformation(Information information);

    /**
     * 获得网站信息
     * @return 系统信息
     */
    Information getInformation();
}
