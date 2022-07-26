package cn.com.njust.controller.home;

import cn.com.njust.entity.Article;
import cn.com.njust.entity.Category;
import cn.com.njust.entity.Tag;
import cn.com.njust.service.ArticleService;
import cn.com.njust.service.TagService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname cn.com.njust.controller.home
 * @Date 2022/2/13 2:26 下午
 * @Created by Zhang Shuheng
 * @Description 根据tag获取文章的控制器
 */
@Controller
public class TagController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;
    @RequestMapping("tag/{tagId}")
    public String getArticleListByTag(@PathVariable("tagId") Integer tagId,
                                      @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                      Model model){
        //调用service层的函数，获取文章列表
        Map<String, Object> criteria=new HashMap<>();
        criteria.put("status",1);
        criteria.put("tagId",tagId);
        PageInfo<Article> articleByPage = articleService.getArticleByPage(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo",articleByPage);

        //获取分类信息。
        Tag tag = tagService.getTagById(tagId);
        model.addAttribute("tag",tag);

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.getTags();
        model.addAttribute("allTagList", allTagList);

        model.addAttribute("pageUrlPrefix", "/tag/"+tagId+"?pageIndex");
        return "home/page/articleListByTag";
    }
}
