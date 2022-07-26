package cn.com.njust.controller.home;

import cn.com.njust.dto.JsonResult;
import cn.com.njust.entity.Article;
import cn.com.njust.entity.Comment;
import cn.com.njust.entity.User;
import cn.com.njust.service.ArticleService;
import cn.com.njust.service.CommentService;
import cn.hutool.http.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

/**
 * @Classname cn.com.njust.controller.home
 * @Date 2022/2/24 3:53 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Controller
public class CommentController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    /**
     * 添加评论
     *
     * @param request
     * @param comment
     */
    @RequestMapping(value = "/comment", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult insertComment(HttpServletRequest request, Comment comment, HttpSession session) {
        //System.out.println(comment+"ssssss2");
        //获取评论者的用户信息
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new JsonResult().fail("请先登录");
        }
        //System.out.println("测试");
        //获取article对象，用来修改文章的评论数
        Article article = articleService.getArticleById(comment.getCommentArticleId());
        article.setArticleCommentCount(article.getArticleCommentCount()+1);
        if (article == null) {
            return new JsonResult().fail("文章不存在");
        }



        //添加评论需要六个数据，分别写入comment数据库，但同时把article数据库的评论数加一
        /*
        前4个是前端传来的，后两个下面的代码设置。
        被评论的id
        Integer commentPid;
        被评论的用户名
        String commentPname;
        评论的哪一篇文章
        Integer commentArticleId;
        评论的内容
        String commentContent;
        评论者的id
        Integer commentUserId;
        评论的时间
        Date commentCreateTime;
        */


        //添加评论，添加评论的用户id
        comment.setCommentUserId(user.getUserId());
        //添加评论的创建时间
        comment.setCommentCreateTime(new Date());

        //过滤字符，防止XSS攻击
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));

        try {
            //插入一个评论。
            commentService.insertComment(comment);
            //更新文章的评论数。
            articleService.updateArticle(article);
            //System.out.println("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().fail();
        }
        return new JsonResult().ok();
    }
}
