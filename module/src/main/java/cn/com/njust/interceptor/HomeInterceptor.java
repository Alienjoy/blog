package cn.com.njust.interceptor;

import cn.com.njust.entity.Category;
import cn.com.njust.entity.Information;
import cn.com.njust.service.CategoryService;
import cn.com.njust.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Classname cn.com.njust.interceptor
 * @Date 2022/2/10 5:49 下午
 * @Created by Zhang Shuheng
 * @Description @Component是组成部分的意思，也是交给Spring来管理对象的创建
 */
@Component
public class HomeInterceptor implements HandlerInterceptor {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private InformationService informationService;

    /**
     * 拦截器，在spring-mvc中配置
     * 在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<Category> categoryList = categoryService.getCategory();
        request.setAttribute("allCategoryList", categoryList);

        Information information = informationService.getInformation();
        //System.out.println(information+"webInformation");
        request.setAttribute("information",information);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
