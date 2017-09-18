package cn.zhku.zhkulife.shiro.listener;

import cn.zhku.zhkulife.po.entity.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/9/13 22:34.
 * @E-mail gonefuture@qq.com
 */
public class UserSessionListener  extends HttpServlet implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
         User user = (User)event.getSession().getAttribute("user");

    }
}
