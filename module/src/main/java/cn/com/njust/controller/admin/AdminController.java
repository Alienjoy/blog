package cn.com.njust.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname cn.com.njust.controller.admin
 * @Date 2022/2/13 3:12 下午
 * @Created by Zhang Shuheng
 * @Description 后台的控制器
 */
@Controller
public class AdminController {
    @RequestMapping("/admin")
    public String admin(){
        return "admin/index";
    }
}
