package cn.com.njust.service.impl;

import cn.com.njust.entity.Information;
import cn.com.njust.mapper.InformationMapper;
import cn.com.njust.service.InformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname cn.com.njust.service.impl
 * @Date 2022/2/10 9:59 下午
 * @Created by Zhang Shuheng
 * @Description service层用来获取网站information的类
 */

@Service
@Slf4j
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationMapper informationMapper;

    @Override
    public int updateInformation(Information information) {
        int res=0;
        try {
            res=informationMapper.updateInformation(information);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改网站信息失败，失败原因为：{}",e);
        }
        return res;
    }

    @Override
    public Information getInformation() {
        Information information=null;
        try {
            information=informationMapper.getInformation();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取网站信息失败，失败原因为：{}",e);
        }
        return information;
    }
}
