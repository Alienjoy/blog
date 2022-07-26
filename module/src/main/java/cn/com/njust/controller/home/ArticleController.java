package cn.com.njust.controller.home;

import cn.com.njust.entity.Article;
import cn.com.njust.entity.Comment;
import cn.com.njust.entity.Tag;
import cn.com.njust.service.ArticleService;
import cn.com.njust.service.CommentService;
import cn.com.njust.service.TagService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Classname cn.com.njust.controller.home
 * @Date 2022/2/11 7:32 下午
 * @Created by Zhang Shuheng
 * @Description  这是文章的controller
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TagService tagService;


    //@PathVariable可以将路径中的内容作为参数
    //文章详情请求
    @RequestMapping("/article/{articleId}")
    public String getArticleDetail(@PathVariable("articleId") Integer articleId, Model model){
        Article article = articleService.getArticleById(articleId);
        //System.out.println("ArticleController查询数据为："+article);
        model.addAttribute("article",article);

        //首级目录是新时间的评论在上面。
        List<Comment> commentList = commentService.getCommentByArticleId(articleId);
        model.addAttribute("commentList",commentList);

        //list深度拷贝
        List<Comment> newCommentList = new ArrayList<>();
        for (int i = 0; i < commentList.size(); i++) {
            newCommentList.add(commentList.get(commentList.size()-i-1));
        }

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.getTags();
        model.addAttribute("allTagList", allTagList);

        //次级评论集合，时间新的在下面
        model.addAttribute("commentListReverse",newCommentList);

        return "home/page/articleDetail";
    }

    /**
     * 用来增加文章浏览量的函数
     * @param id
     * @return
     */
    @RequestMapping(value = "/article/view/{id}", method = {RequestMethod.POST}, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String increaseViewCount(@PathVariable("id") Integer id) {
        Article article = articleService.getArticleById(id);
        Integer articleCount = article.getArticleViewCount() + 1;
        article.setArticleViewCount(articleCount);
        articleService.updateArticle(article);
        return JSON.toJSONString(articleCount);
    }

}
