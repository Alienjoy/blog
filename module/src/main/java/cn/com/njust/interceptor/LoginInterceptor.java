package cn.com.njust.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname cn.com.njust.interceptor
 * @Date 2022/2/27 1:25 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    //预处理之前，就是controller之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("user")==null){
            response.sendRedirect("/login");
            //不继续执行下面的controller
            return false;
        }
        return true;
    }

    //controller之后，但是在渲染界面之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //在渲染完界面之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
