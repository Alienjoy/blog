package cn.com.njust.controller.admin;

import cn.com.njust.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Classname cn.com.njust.controller.admin
 * @Date 2022/2/28 3:22 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Controller
@Slf4j
@RequestMapping("admin/profile")
public class AdminUserController {

    @RequestMapping(value = "")
    public ModelAndView userList(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");

        modelAndView.addObject("user", user);

        modelAndView.setViewName("admin/User/profile");
        return modelAndView;

    }
}
