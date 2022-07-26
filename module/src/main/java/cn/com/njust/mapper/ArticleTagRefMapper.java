package cn.com.njust.mapper;

import cn.com.njust.entity.Article;
import cn.com.njust.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname cn.com.njust.mapper
 * @Date 2022/2/11 8:50 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Mapper
public interface ArticleTagRefMapper {
    //根据文章的id获取它的标签
    public List<Tag> getTagsByArticleId(Integer articleID);

    //插入文章和标签的索引
    public int insertArticleTagRef(@Param("article") Article article);

    void deleteTagById(Integer articleId);
}