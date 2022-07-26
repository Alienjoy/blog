package cn.com.njust.controller;

import cn.com.njust.entity.User;
import cn.com.njust.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname cn.com.njust.controller
 * @Date 2022/2/18 1:54 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @RequestMapping("register")
    public String register(){
        return "admin/register";
    }

    @RequestMapping(value = "/registerSubmit", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(username, password, nickname, email);
        User userByUserName = userService.getUserByUserNameOrEmail(username);
        User userByEmail = userService.getUserByUserNameOrEmail(email);
        //如果用户名和邮箱已被注册了，则返回
        if(userByUserName!=null){
            map.put("code", 0);
            map.put("msg", "用户名已被占用");
        }else if(userByEmail!=null){
            map.put("code", 0);
            map.put("msg", "此邮箱已被注册");
        }else {
            int i = userService.insertUser(user);
            if (i==1){
                map.put("code", 1);
                map.put("msg", "注册成功");
            }else {
                map.put("code", 0);
                map.put("msg", "注册失败，请稍后重试");
            }
        }

        String result = new JSONObject(map).toString();

        return result;
    }
}
