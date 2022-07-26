package cn.com.njust.service.impl;

import cn.com.njust.entity.Article;
import cn.com.njust.entity.Category;
import cn.com.njust.entity.Tag;
import cn.com.njust.entity.User;
import cn.com.njust.mapper.ArticleCategoryRefMapper;
import cn.com.njust.mapper.ArticleMapper;
import cn.com.njust.mapper.ArticleTagRefMapper;
import cn.com.njust.mapper.UserMapper;
import cn.com.njust.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Classname cn.com.njust.service.impl
 * @Date 2022/2/11 2:04 下午
 * @Created by Zhang Shuheng
 * @Description  service层的实现类，对dao层读到的数据进行包装。
 */

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;
    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;
    @Autowired
    private UserMapper userMapper;


    //根据查询条件，按页获取数据。
    @Override
    public PageInfo<Article> getArticleByPage(Integer pageIndex,
                                        Integer pageSize,
                                        Map<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        //调用dao层的数据
        List<Article> articleList=articleMapper.getArticle(criteria);
        //给每一篇文章添加分类信息（首页不需要显示文章的tag信息和用户信息）
        for (Article article : articleList) {
            //根据id获取分类
            List<Category> articleCategory = articleCategoryRefMapper.getCategoryByArticleId(article.getArticleId());
            article.setCategoryList(articleCategory);
            //根据用户id获取用户信息
            User user = userMapper.getUserById(article.getArticleUserId());
            article.setUser(user);

        }
        return new PageInfo<>(articleList);
    }

    //根据id获取文章的详情
    @Override
    public Article getArticleById(Integer articleId) {
        try {
            Article article = articleMapper.getArticleById(articleId);
            //根据id获取分类
            List<Category> articleCategory = articleCategoryRefMapper.getCategoryByArticleId(article.getArticleId());
            article.setCategoryList(articleCategory);
            //根据id获取标签
            List<Tag> articleTags = articleTagRefMapper.getTagsByArticleId(article.getArticleId());
            article.setTagList(articleTags);
            //根据用户获取用户信息
            User articleUser = userMapper.getUserById(article.getArticleUserId());
            article.setUser(articleUser);
            return article;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id查询文章列表失败，失败原因为：{}",e);
            return null;
        }

    }

    @Override

    public int insertArticle(Article article) {
        try {
            //System.out.println(article.getArticleThumbnail());
            if(article.getArticleThumbnail().isEmpty()){
                article.setArticleThumbnail("/img/thumbnail/random/img_" + new Random().nextInt(10) + ".jpg");
            }
            //插入文章
            articleMapper.insertArticle(article);
            //插入文章后article id就有了。
            //System.out.println("ssssssss2"+article);
            //插入文章和分类索引
            articleCategoryRefMapper.insertArticleCategoryRef(article);

            if(article.getTagList().size()!=0) {
                //插入文章和标签索引
                articleTagRefMapper.insertArticleTagRef(article);
            }

            return 1;
        }catch (Exception e){
            e.printStackTrace();
            log.error("插入新文章失败，失败原因为：{}",e);

        }
        return 0;
    }

    /**
     * 根据id删除文章，也要删除文章对象的分类和tag
     * @param articleId
     * @return
     */
    @Override
    public int deleteArticle(Integer articleId) {
        try {
            //删除文章
            articleMapper.deleteArticleById(articleId);
            //删除对应的分类
            articleCategoryRefMapper.deleteCategoryById(articleId);
            //删除对应的tag
            articleTagRefMapper.deleteTagById(articleId);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            log.error("删除文章失败，失败原因为{}",e);
        }
        return 0;
    }

    /**
     * 更新文章内容
     * @param article
     * @return
     */
    @Override
    public int updateArticle(Article article) {
        try {
            articleMapper.updateArticle(article);
            //删除后添加新的
            articleCategoryRefMapper.deleteCategoryById(article.getArticleId());
            articleCategoryRefMapper.insertArticleCategoryRef(article);
            articleTagRefMapper.deleteTagById(article.getArticleId());

            if(article.getTagList().size()!=0){
                articleTagRefMapper.insertArticleTagRef(article);
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            log.error("更新文章内容失败，失败原因为：{}",e);
        }
        return 0;
    }
}
