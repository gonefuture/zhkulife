package cn.zhku.zhkulife.modules.user.controller;

import cn.zhku.zhkulife.modules.user.service.UserService;
import cn.zhku.zhkulife.po.entity.User;
import cn.zhku.zhkulife.po.mapper.UserMapper;
import cn.zhku.zhkulife.utils.Beans.Message;

import cn.zhku.zhkulife.utils.yiBanUtils.YiBanAuth;
import cn.zhku.zhkulife.utils.yiBanUtils.YiBanUserUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 22:42.
 * @E-mail gonefuture@qq.com
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;


    /**
     *    普通用户登录
     * @param form   必须参数 userId userPassword
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("user/login")
    @ResponseBody
    public Message login(User form,HttpSession httpSession,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception {

        User user =  userService.login(form);

        if (user == null){
            return new Message("2","登录失败，用户不存在或密码错误");
        }
        else if (user.getUserPassword().equals("123456")){
            user.setUserPassword(null);
            httpSession.setAttribute("user",user);
            System.out.println(YiBanAuth.getUserMe(httpServletRequest,httpServletResponse));
            return new Message("3", "登录成功，密码过于简单，不能为123456", user.getUserId());
        }
        else if ( user.getUserPhone() ==null || user.getUserPhone().equals("0")){
            user.setUserPassword(null);
            httpSession.setAttribute("user",user);
            System.out.println(YiBanAuth.getUserMe(httpServletRequest,httpServletResponse));
            return new Message("3", "登录成功，手机号不能为空", user.getUserId());
        }
        else {
            user.setUserPassword(null);
            httpSession.setAttribute("user",user);
            System.out.println(YiBanAuth.getUserMe(httpServletRequest,httpServletResponse));
            return new Message("1", "登录成功", user.getUserId());
        }
    }

    @RequestMapping("office/user/list")
    @ResponseBody
    public PageInfo<User> list(String pageNum,String pageSize,User user) throws Exception {
        if (pageNum == null)
            pageNum = "1";
        if (pageSize == null)
            pageSize = "10";
        PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        return new PageInfo<User>(userService.findAll(user));
    }

    @RequestMapping("office/user/edit")
    @ResponseBody
    public Message updateUser(User user) throws Exception {
        if (userService.update(user)  == 1)
            return new Message("2","修改普通用户成功");
        else
            return new Message("1","修改普通用户失败");
    }

    @RequestMapping("office/user/delete")
    @ResponseBody
    public Message deleteUser(User user) throws Exception {
        if (userService.delete(user)  == 1)
            return new Message("2","删除普通用户成功");
        else
            return new Message("1","删除普通用户失败");
    }





    @RequestMapping("user/updatePassword")
    @ResponseBody
    public Message updatePassword(HttpSession httpSession,String password) throws Exception {
        User userCache = (User) httpSession.getAttribute("user");
        User user = new User();
        user.setUserId(userCache.getUserId()); user.setUserPassword(password);
        if (userCache == null )
            return new Message("2","请先登录");
        else if (userService.update(user) != 1)
            return new Message("2","修改密码失败，请检查参数");
        else
            return new Message("1","修改密码成功");

    }

    @RequestMapping("user/updatePhone")
    @ResponseBody
    public Message updatePhone(HttpSession httpSession,String phone) throws Exception {
        User userCache = (User) httpSession.getAttribute("user");
        User user = new User();
        user.setUserId(userCache.getUserId()); user.setUserPhone(phone);
        if (userService.update(user) != 1)
            return new Message("2","修改手机号码失败");
        else
            return new Message("1","修改手机号码成功");
    }

    @RequestMapping("user/getIt")
    @ResponseBody
    public  User getUserIt(HttpSession httpSession) throws Exception {
        User userCache = (User) httpSession.getAttribute("user");
        return  userService.get(userCache.getUserId());
    }

}
