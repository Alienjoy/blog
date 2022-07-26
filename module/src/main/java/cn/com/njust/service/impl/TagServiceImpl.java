package cn.com.njust.service.impl;

import cn.com.njust.entity.Tag;
import cn.com.njust.mapper.TagMapper;
import cn.com.njust.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Classname cn.com.njust.service.impl
 * @Date 2022/2/13 2:45 下午
 * @Created by Zhang Shuheng
 * @Description
 */

@Service
@Slf4j
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public Tag getTagById(Integer tagId) {
        try {
            Tag tag = tagMapper.getTagById(tagId);
            return tag;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取标签失败，失败原因为：{}", e);
        }
        return null;
    }

    @Override
    public List<Tag> getTags() {
        try {
            List<Tag> tags = tagMapper.getTags();
            return tags;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取标签失败，失败原因为：{}", e);
        }
        return null;
    }
}
