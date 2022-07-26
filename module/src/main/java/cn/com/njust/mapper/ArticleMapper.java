package cn.com.njust.mapper;

import cn.com.njust.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname cn.com.njust.mapper
 * @Date 2022/2/11 3:29 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Mapper
public interface ArticleMapper {


    /**
     * 获取所有的文章，按照时间排
     *
     * @param criteria 参数，有下列五个
     *                 #status 状态，草稿0，已发布1
     *                 #keywords 关键词，
     *                 #userId 用户名
     *                 #categoryld 分类
     *                 #tagId 标签
     * @return
     */
    List<Article> getArticle(Map<String, Object> criteria);

    //根据id获取文章
    Article getArticleById(@Param("articleID") Integer articleID);

    //把article插入到数据库
    int insertArticle(Article article);
    //根据文章id删除文章
    int deleteArticleById(Integer articleId);

    //更新文章
    int updateArticle(Article article);
}