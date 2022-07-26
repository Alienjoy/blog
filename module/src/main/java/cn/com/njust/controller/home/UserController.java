package cn.com.njust.controller.home;

import cn.com.njust.entity.Article;
import cn.com.njust.entity.Tag;
import cn.com.njust.entity.User;
import cn.com.njust.service.ArticleService;
import cn.com.njust.service.TagService;
import cn.com.njust.service.UserService;
import com.github.pagehelper.PageInfo;
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
 * @Date 2022/2/28 2:44 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Controller
public class UserController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @RequestMapping("/user/{userName}")
    public String getArticleByCategory(@PathVariable("userName") String userName,
                                       @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                       Model model){

        Map<String, Object> criteria=new HashMap<>();
        User user = userService.getUserByUserNameOrEmail(userName);
        criteria.put("userId",user.getUserId());
        PageInfo<Article> articleByPage = articleService.getArticleByPage(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo",articleByPage);
        model.addAttribute("user",user);
        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.getTags();
        model.addAttribute("allTagList", allTagList);
        //页面链接的前缀
        model.addAttribute("pageUrlPrefix","/user/"+userName+"?pageIndex");
        return "home/page/articleListByUser";
    }
}
