package cn.com.njust.mapper;

import cn.com.njust.entity.Article;
import cn.com.njust.entity.Category;
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
public interface ArticleCategoryRefMapper {
    //根据文章的id获取他所属于的类别
    public List<Category> getCategoryByArticleId(Integer articleID);

    //插入文章和分类的索引
    int insertArticleCategoryRef(@Param("article") Article article);

    //删除文章和分类的索引
    void deleteCategoryById(Integer articleId);


}    