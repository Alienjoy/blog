package cn.com.njust.controller.admin;

import cn.com.njust.entity.Article;
import cn.com.njust.entity.Comment;
import cn.com.njust.entity.User;
import cn.com.njust.service.ArticleService;
import cn.com.njust.service.CommentService;
import cn.com.njust.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

/**
 * @Classname cn.com.njust.controller.admin
 * @Date 2022/2/27 2:10 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Controller
@RequestMapping("admin/comment")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String adminComment(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                               HttpSession session,
                               Model model){
        User user = (User) session.getAttribute("user");
        HashMap<String,Object> criteria= new HashMap<>();
        criteria.put("userId",user.getUserId());
        PageInfo<Comment> commentListByPage = commentService.getCommentListByPage(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", commentListByPage);
        model.addAttribute("pageUrlPrefix", "/admin/comment?pageIndex");
        return "admin/Comment/index";
    }

    @RequestMapping("/receive")
    public String adminCommentReply(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                               HttpSession session,
                               Model model){
        User user = (User) session.getAttribute("user");
        HashMap<String,Object> criteria= new HashMap<>();
        criteria.put("puserId",user.getUserId());
        PageInfo<Comment> commentListByPage = commentService.getCommentListByPage(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", commentListByPage);
        model.addAttribute("pageUrlPrefix", "/admin/comment?pageIndex");
        return "admin/Comment/receive";
    }

    /**
     * 用来删除评论的controller层的代码,js来调用的
     * @param id
     * @param session
     */
    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST)
    public void deleteArticle(@PathVariable("id") Integer id, HttpSession session){
        User user = (User)session.getAttribute("user");
        Comment comment=commentService.getCommentById(id);

        //如果没有
        if (comment==null){
            return ;
        }

        //如果用户角色为admin或则是本人发布的评论，或则是别人发给自己的。才可以删除评论

        if(user.getUserRole().equals("admin")||user.getUserId().equals(comment.getCommentUserId())||user.getUserId().equals(comment.getCommentPuserId())){
            Article article = articleService.getArticleById(comment.getCommentArticleId());
            article.setArticleCommentCount(article.getArticleCommentCount()-1);
            articleService.updateArticle(article);//修改文章的评论数

            commentService.deleteCommentById(id);//删除文章。
        }
        //否则返回
        return;

    }


    //这是通过回复按钮来跳转到的页面
    @RequestMapping(value = "reply/{cid}")
    public String replyComment(@PathVariable("cid") Integer cid, HttpSession session,Model model){
        Comment comment = commentService.getCommentById(cid);
        User user = userService.getUserById(comment.getCommentUserId());
        comment.setUser(user);
        model.addAttribute("comment",comment);
        return "admin/Comment/reply";
    }

    //回复评论提交，
    @RequestMapping("replySubmit")
    public String replySubmit(HttpSession session,Comment comment){
        User user = (User)session.getAttribute("user");
        //封装评论的发布者
        comment.setCommentUserId(user.getUserId());
        //封装回复评论的时间
        comment.setCommentCreateTime(new Date());
        commentService.insertComment(comment);
        return "redirect:/admin/comment";
    }

}
