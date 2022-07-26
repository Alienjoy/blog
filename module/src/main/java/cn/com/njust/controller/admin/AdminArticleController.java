package cn.com.njust.controller.admin;

import cn.com.njust.dto.ArticleForm;
import cn.com.njust.entity.Article;
import cn.com.njust.entity.Category;
import cn.com.njust.entity.Tag;
import cn.com.njust.entity.User;
import cn.com.njust.service.ArticleService;
import cn.com.njust.service.CategoryService;
import cn.com.njust.service.TagService;
import cn.com.njust.utils.JsoupUtil;
import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Classname cn.com.njust.controller.admin
 * @Date 2022/2/18 2:15 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Controller
@Slf4j
@RequestMapping("admin/article")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    /**
     * 后台文章的主页面
     * @param pageIndex
     * @param pageSize
     * @param status
     * @param model
     * @param session
     * @return
     */
    //controller是单例模式，对于一个浏览器请求，tomcat会指定一个处理线程，或是在线程池中选取空闲的，或者新建一个线程
    @RequestMapping("")
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        @RequestParam(required = false) Integer status,
                        Model model, HttpSession session) {
        Map<String, Object> criteria = new HashMap<>(1);
        if (status == null) {
            //给分页符号添加链接的前缀
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        } else {
            criteria.put("status", status);
            model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        }
        //看session，就是登陆后会给
        User user = (User)session.getAttribute("user");
        if(!"admin".equals(user.getUserRole())){
            //如果不是admin的话，添加userId查询条件
            criteria.put("userId",user.getUserId());
        }
        PageInfo<Article> articleByPage = articleService.getArticleByPage(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articleByPage);
        model.addAttribute("status",status);
        return "admin/Article/index";
    }

    /**
     * 显示新建文章的界面数据
     * @param model
     * @return
     */
    @RequestMapping(value = "/insert")
    public String insertArticle(Model model) {
        //获取所有的分类
        List<Category> categoryList = categoryService.getCategory();
        //获取所有的tag
        List<Tag> tagList = tagService.getTags();
        //新建文章的分类和标签是从数据库获取的
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("tagList", tagList);
        return "admin/Article/insert";
    }


    /**
     * 后台添加文章 提交操作
     *
     * @param articleForm
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST,produces="text/html;charset=utf-8")
    public String insertArticleSubmit(HttpSession session, ArticleForm articleForm) {
        //System.out.println("SSSSSSSSS"+articleForm);
        Article article = new Article();
        //对article Bean绑定用户Id
        User user = (User) session.getAttribute("user");
        //System.out.println("ssssss"+user);
        if (user != null) {
            article.setArticleUserId(user.getUserId());
        }
        //标题
        //转义字符，防止XSS攻击
        article.setArticleTitle(HtmlUtil.escape(articleForm.getArticleTitle()));



        //防御XSS攻击,利用jsoup添加白名单，安全HTML验证
        String safeHtml=JsoupUtil.clean(articleForm.getArticleContent());
        articleForm.setArticleContent(safeHtml);
        //清楚掉html标签
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(safeHtml);
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        //缩略图
        article.setArticleThumbnail(articleForm.getArticleThumbnail());
        //内容
        article.setArticleContent(articleForm.getArticleContent());
        //状态
        article.setArticleStatus(articleForm.getArticleStatus());

        //article数据库，填充分类，获取表格的父类和子类的数据，把id新建成category
        List<Category> categoryList = new ArrayList<>();
        if (articleForm.getArticleParentCategoryId() != null) {
            //不需要分类的全部信息，只需要分类的id就可以
            categoryList.add(new Category(articleForm.getArticleParentCategoryId()));
        }
        if (articleForm.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleForm.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        //article填充标签，根据id新建Tag的对象
        List<Tag> tagList = new ArrayList<>();
        if (articleForm.getArticleTagIds() != null) {
            for (int i = 0; i < articleForm.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleForm.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        //把article封装好了，然后插入article
        article.setArticleViewCount(0);
        article.setArticleCommentCount(0);
        article.setArticleLikeCount(0);
        article.setArticleIsComment(1);
        Date date = new Date();
        article.setArticleCreateTime(date);
        article.setArticleUpdateTime(date);
        //System.out.println("ssssssss1"+article);
        articleService.insertArticle(article);

        return "redirect:/admin/article";
    }



    /**
     * 利用ajax技术来更新页面，来进行删除，在back.js中会调用这个。
     * @param id
     * @param session
     */
    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST)
    public void deleteArticle(@PathVariable("id") Integer id, HttpSession session){
        Article article = articleService.getArticleById(id);
        //如果没有
        if (article==null){
            return ;
        }
        User user = (User) session.getAttribute("user");
        //如果用户角色为admin或则是本人发布的文章，才可以删除文章
        if(user.getUserRole().equals("admin")||user.getUserId().equals(article.getArticleUserId())){
            articleService.deleteArticle(id);
        }
        //否则返回
        return;

    }

    //编辑文章的页面
    @RequestMapping("/edit/{id}")
    public String editArticle(@PathVariable("id") Integer articleId,HttpSession session,Model model){

        Article article = articleService.getArticleById(articleId);

        String safeHtml=JsoupUtil.clean(article.getArticleContent());
        //System.out.println(safeHtml+"edit");
        article.setArticleContent(safeHtml);

        if(article==null){
            //如果没有的话重定向到404，就是localhost:8080/404
            return "redirect:/404";
        }
        //如果不是admin，或则不是自己的文章，重定向到403
        User user = (User) session.getAttribute("user");
        if(user.getUserRole().equals("admin")||user.getUserId().equals(article.getArticleUserId())){//添加文章属性
            model.addAttribute("article",article);
            //获取所有的分类
            List<Category> categoryList = categoryService.getCategory();
            //获取所有的tag
            List<Tag> tagList = tagService.getTags();
            //修改文章的分类和标签是从数据库获取的
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("tagList", tagList);
            return "admin/Article/edit";
        }else {
            //如果不是admin，并且也不是自己的文章，则没有权限修改文章，重定向到403
            return "redirect:/403";
        }
    }

    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST,produces="text/html;charset=utf-8")
    public String editSubmit(ArticleForm articleForm){
        Article article = articleService.getArticleById(articleForm.getArticleId());

        //标题
        //转义字符，防止XSS攻击
        article.setArticleTitle(HtmlUtil.escape(articleForm.getArticleTitle()));

        //防御XSS攻击,利用jsoup添加白名单，安全HTML验证
        String safeHtml=JsoupUtil.clean(articleForm.getArticleContent());
        //System.out.println(safeHtml);
        article.setArticleContent(safeHtml);
        //清楚掉html标签
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(safeHtml);
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        //缩略图
        article.setArticleThumbnail(articleForm.getArticleThumbnail());

        //状态
        article.setArticleStatus(articleForm.getArticleStatus());
        //article数据库，填充分类，获取表格的父类和子类的数据，把id新建成category
        List<Category> categoryList = new ArrayList<>();
        if (articleForm.getArticleParentCategoryId() != null) {
            //不需要分类的全部信息，只需要分类的id就可以
            categoryList.add(new Category(articleForm.getArticleParentCategoryId()));
        }
        if (articleForm.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleForm.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        //article填充标签，根据id新建Tag的对象

        if (articleForm.getArticleTagIds() != null) {
            List<Tag> tagList = new ArrayList<>();
            for (int i = 0; i < articleForm.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleForm.getArticleTagIds().get(i));
                tagList.add(tag);
            }
            article.setTagList(tagList);
        }



        Date date = new Date();
        article.setArticleUpdateTime(date);

        articleService.updateArticle(article);
        return "redirect:/article/"+articleForm.getArticleId();
    }
}
