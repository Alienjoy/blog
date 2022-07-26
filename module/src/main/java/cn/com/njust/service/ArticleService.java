package cn.com.njust.service;

import cn.com.njust.entity.Article;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname cn.com.njust.service
 * @Date 2022/2/11 2:03 下午
 * @Created by Zhang Shuheng
 * @Description
 */
public interface ArticleService {
    /**
     * 根据条件查询所有的文章，按照时间顺序排序，
     * criteria包括五个
     * #status 状态
     * #keywords 关键词
     * #userId 用户名
     * #categoryId 分类
     * #tagId 标签
     * @return 返回一个页面的数据
     */
    PageInfo<Article> getArticleByPage(Integer pageIndex,
                                       Integer pageSize,
                                       Map<String, Object> criteria);

    /**
     * 根据id获取文章的内容，获取文章的详情用
     * @param articleId
     * @return
     */
    Article getArticleById(Integer articleId);

    //把文章保存到数据库，用来新建文章。
    public int insertArticle(Article article);

    //根据id删除文章
    public int deleteArticle(Integer articleId);

    //用来更新文章的信息
    public int updateArticle(Article article);

}
