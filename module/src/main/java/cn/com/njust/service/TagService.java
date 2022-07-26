package cn.com.njust.service;

import cn.com.njust.entity.Tag;

import java.util.List;

/**
 * @Classname cn.com.njust.service
 * @Date 2022/2/13 2:44 下午
 * @Created by Zhang Shuheng
 * @Description
 */
public interface TagService {
    //根据id获取tag
    public Tag getTagById(Integer tagId);
    //获取所有的tag
    public List<Tag> getTags();
}
