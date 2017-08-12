package cn.zhku.zhkulife.shiro.interceptor;

import cn.zhku.zhkulife.po.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/10 21:59.
 * @E-mail gonefuture@qq.com
 */
public class UserInterceptor implements HandlerInterceptor {
    private  static final String[] IGNORE_URL = {"/user/login"};

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag = false;
        String servletPath = httpServletRequest.getServletPath();

        for (String url : IGNORE_URL) {
            if (servletPath.contains(url)) {
                flag = true;
                break;
            }
        }
        //拦截请求
        if(!flag) {
            User user = (User) httpServletRequest.getSession().getAttribute("user");
            if(user == null) {
                httpServletRequest.setAttribute("msg","请先登录再访问网站");
                httpServletRequest.getRequestDispatcher("/errors").forward(httpServletRequest,httpServletResponse);
            } else {
                flag =true;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
