package cn.com.njust.controller.home;

import cn.com.njust.entity.Article;
import cn.com.njust.entity.Comment;
import cn.com.njust.entity.Notice;
import cn.com.njust.entity.Tag;
import cn.com.njust.service.ArticleService;
import cn.com.njust.service.CommentService;
import cn.com.njust.service.NoticeService;
import cn.com.njust.service.TagService;
import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname cn.com.njust.controller.home
 * @Date 2022/2/9 5:43 下午
 * @Created by Zhang Shuheng
 * @Description  这是首页的控制层
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = {"/","/article","index"})
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model) {
        Map<String, Object> criteria=new HashMap<>();
        criteria.put("status",1);
        PageInfo<Article> articleByPage = articleService.getArticleByPage(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articleByPage);
        List<Notice> notices = noticeService.getNotices();
        model.addAttribute("noticeList",notices);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.getTags();
        model.addAttribute("allTagList", allTagList);
        //近期评论
        List<Comment> recentCommentList = commentService.listRecentComment(null, 10);
        model.addAttribute("recentCommentList", recentCommentList);

        //下面的是底部的分页组件
        model.addAttribute("pageUrlPrefix", "/article?pageIndex");
        return "home/index";
    }

    @RequestMapping(value = "/search")
    public String search(
            //required默认值为true
            @RequestParam("keywords") String keywords,
            @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model) {
        //文章列表
        //防止xss攻击
        //System.out.println("before:"+keywords);
        keywords=HtmlUtil.escape(keywords);
        //System.out.println("after:"+keywords);

        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", 1);
        criteria.put("keywords", keywords);
        PageInfo<Article> articlePageInfo = articleService.getArticleByPage(pageIndex, pageSize, criteria);
        model.addAttribute("keywords",keywords);
        model.addAttribute("pageInfo", articlePageInfo);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.getTags();
        model.addAttribute("allTagList", allTagList);
//        //获得随机文章
//        List<Article> randomArticleList = articleService.listRandomArticle(8);
//        model.addAttribute("randomArticleList", randomArticleList);
//        //获得热评文章
//        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
//        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
//        //最新评论
//        List<Comment> recentCommentList = commentService.listRecentComment(null, 10);
//        model.addAttribute("recentCommentList", recentCommentList);
        model.addAttribute("pageUrlPrefix", "/search?pageIndex");
        return "home/page/search";
    }

}
