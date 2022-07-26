package cn.com.njust.mapper;

import cn.com.njust.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname cn.com.njust.mapper
 * @Date 2022/2/11 8:46 下午
 * @Created by Zhang Shuheng
 * @Description 
 */
@Mapper
public interface TagMapper {
    //根据id获取tag
    public Tag getTagById(Integer tagId);
    //获取所有的tags
    public List<Tag> getTags();
}