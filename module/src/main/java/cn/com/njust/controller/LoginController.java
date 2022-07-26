package cn.com.njust.controller;

import cn.com.njust.entity.User;
import cn.com.njust.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname cn.com.njust.controller
 * @Date 2022/2/13 3:17 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Controller
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    //登陆，地址输入login会跳转到login语句
    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }


    /**
     * 登录验证，点登陆按钮后会执行下面的语句
     *
     * @param request
     * @param response
     * @return ResponseBody是将java对象转为json格式的数据，然后返回，用于异步返回数据，没有跳转
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        //System.out.println(rememberme+"rememberMe");
        User user = userService.getUserByUserNameOrEmail(username);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "用户名无效！");
        //下面的是验证
        } else if (!user.getUserPass().equals(password)) {
            map.put("code", 0);
            map.put("msg", "密码错误！");
        } else if (user.getUserStatus() == 0) {
            map.put("code", 0);
            map.put("msg", "账号已禁用！");
        } else {
            //返回map，code=1表示登录成功
            map.put("code", 1);
            map.put("msg", "");
            //获取session，添加user属性
            request.getSession().setAttribute("user", user);
            request.getSession().setMaxInactiveInterval(60*60*24*4);//设置4天,-1表示永不过期

            Cookie jsessionid = null;
            try {
                jsessionid = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            //设置jsessionid的声明周期，三天，但是tomcat session的存活时间是默认的20分钟。
            jsessionid.setMaxAge(60*60*24*3);
            response.addCookie(jsessionid);
            //如果勾选了记住密码，添加cookie
            if (rememberme != null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }else{
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", null);
                pwdCookie.setMaxAge(0);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginTime(new Date());
            //user.setUserLastLoginIp(getIpAddr(request));
            userService.updateUser(user);

        }
        String result = new JSONObject(map).toString();
        //System.out.println(result);
        return result;
    }

    //退出登陆
    @RequestMapping("admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        //使这个session无效。
        session.invalidate();
        //从定向到首页
        return "redirect:/";
    }

}
