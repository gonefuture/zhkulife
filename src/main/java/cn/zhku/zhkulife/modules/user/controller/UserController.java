package cn.zhku.zhkulife.modules.user.controller;

import cn.zhku.zhkulife.modules.user.service.UserService;
import cn.zhku.zhkulife.po.entity.User;
import cn.zhku.zhkulife.utils.Beans.CommonQo;
import cn.zhku.zhkulife.utils.Beans.Message;

import cn.zhku.zhkulife.utils.Beans.UserMe;
import cn.zhku.zhkulife.utils.Beans.YiBanUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 22:42.
 * @E-mail gonefuture@qq.com
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;


    /**
     *    普通用户登录
     * @param form   必须参数 userId userPassword
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("user/login")
    @ResponseBody
    public Message login(User form,HttpSession httpSession) throws Exception {

        User user =  userService.login(form);

        if (user == null){
            return new Message("2","登录失败，用户不存在或密码错误,或者你的舍友已经改了密码，请询问他们确认");
        }
//        else if (user.getUserPassword().equals("123456")){
//            afterLogin(user,httpSession);
//            httpSession.setAttribute("user",user);
//            return new Message("3", "登录成功，密码过于简单，不能为123456", user.getUserId());
//        }
//        else if ( user.getUserPhone() ==null || user.getUserPhone().equals("0")){
//            afterLogin(user,httpSession);
//            return new Message("3", "登录成功，手机号不能为空", user.getUserId());
//        }
        else {
            afterLogin(user,httpSession);
            return new Message("1", "登录成功", user.getUserId());
        }
    }

    /**
     *  登录后的事务
     * @param user  用户信息
     * @param httpSession   当前session
     * @throws Exception    sqlException
     */
    private void afterLogin(User user,HttpSession httpSession) throws Exception {
        httpSession.setAttribute("user",user);
        Object yibanIfo = httpSession.getAttribute("yibanInfo");    //  获取用户信息
        if (yibanIfo == null) {

        } else {
            UserMe userMe = (UserMe) yibanIfo;
            user.setYibanInfo(userMe.toString());
        }
        userService.update(user);
    }

    /**        多条件查找普通用户
     *
     * @param commonQo   查询通用类
     * @param user  参数：userZone  ,userId   , userPhone   , total_water
     * @return  用户列表
     * @throws Exception   sqlException
     */
    @RequestMapping("office/user/list")
    @ResponseBody
    public PageInfo<User> list(CommonQo commonQo, User user) throws Exception {
        PageHelper.startPage(commonQo.getPageNum(),commonQo.getPageSize(),"user_id desc");
        return new PageInfo<User>(userService.findAll(user));
    }

    /**
     *  修改普通用户
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("office/user/edit")
    @ResponseBody
    public Message updateUser(User user) throws Exception {
        if (userService.update(user)  == 1)
            return new Message("2","修改普通用户成功");
        else
            return new Message("1","修改普通用户失败");
    }

    /**
     *  删除普通用户
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("office/user/delete")
    @ResponseBody
    public Message deleteUser(User user) throws Exception {
        if (userService.delete(user)  == 1)
            return new Message("2","删除普通用户成功");
        else
            return new Message("1","删除普通用户失败");
    }

    /**
     *  添加普通用户
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("office/user/add")
    @ResponseBody
    public Message addUser(User user) {
        try {
            if (userService.add(user) == 1)
                return new Message("2", "增加普通用户成功");
            else {
                return new Message("2","添加普通用户失败,请确认填写的信息是否都正确");
            }
        } catch (DuplicateKeyException duplicateKeyException) {
            return new Message("2", "用户已经存在，请不要重复添加");
        } catch (Exception e) {
            return new Message("2", "出现其他错误了。请联系开发者");
        }

    }



    /** 普通用户修改密码
     * @param httpSession  当前会话
     * @param password 新密码
     * @return   Message
     * @throws Exception   sql
     */
    @RequestMapping("user/updatePassword")
    @ResponseBody
    public Message updatePassword(HttpSession httpSession,String password) throws Exception {
        User userCache = (User) httpSession.getAttribute("user");    //获取session的user
        User user = new User();
        user.setUserId(userCache.getUserId()); user.setUserPassword(password);

        if (userService.update(user) != 1){
            return new Message("2","修改密码失败，请检查参数");
        }
        else {
            refreshSession(userCache,httpSession);    //刷新修改后的session
            return new Message("1","修改密码成功");
        }

    }

    /**    修改用户手机号
     *
     * @param httpSession   当前会话
     * @param phone   新手机号
     * @return   Message
    `     * @throws Exception  sql
     */
    @RequestMapping("user/updatePhone")
    @ResponseBody
    public Message updatePhone(HttpSession httpSession,String phone) throws Exception {
        User userCache = (User) httpSession.getAttribute("user");   //获取session的user
        User user = new User();
        user.setUserId(userCache.getUserId()); user.setUserPhone(phone);
        if (userService.update(user) != 1){
            return new Message("2","修改手机号码失败");
        }
        else {
            refreshSession(userCache,httpSession);    //刷新修改后的session
            return new Message("1", "修改手机号码成功");
        }
    }

    /**     修改的密码和手机
     *
     * @param httpSession  当前会话
     * @param phone   手机号
     * @param password  密码
     * @return
     * @throws Exception
     */
    @RequestMapping("user/updateData")
    @ResponseBody
    public Message updateData(HttpSession httpSession,String phone,String password) throws Exception {
        User userCache = (User) httpSession.getAttribute("user");
        User user = new User();
        user.setUserId(userCache.getUserId()); user.setUserPhone(phone);user.setUserPassword(password);
        if (userService.update(user) != 1){
            return new Message("2","修改手机号码和密码失败");
        }
        else {
            refreshSession(userCache,httpSession);    //刷新修改后的session
            return new Message("1","修改手机号码和密码成功");
        }
    }

    /** 刷新修改用户信息后的session
     *
     * @param userCache   当前用户信息session
     * @param httpSession  当前会话
     * @throws Exception  sql
     */

    public void refreshSession(User userCache,HttpSession httpSession) throws Exception {
        User user = (User) userService.get(userCache.getUserId());
        System.out.println(httpSession.getAttribute("user"));
        httpSession.setAttribute("user",user);
        System.out.println(httpSession.getAttribute("user"));
    }


    /**         获取用户信息
     *
     * @param httpSession   当前会话
     * @return  User实体
     * @throws Exception  SQLException
     */
    @RequestMapping("user/get")
    @ResponseBody
    public  User getUserIt(HttpSession httpSession) throws Exception {
        User userCache = (User) httpSession.getAttribute("user");
        return  userService.get(userCache.getUserId());
    }


    /**usercontroller.java
      * 修改人:李龙杰
      * 创建时间:2017-10-24 19:43
      * 修改时间:2017-10-24 19:43
      *通过用户ID查找某个单一用户
      * @param user  参数：userId
      * @return  用户对象
      * @throws Exception   sqlException
      */
    @RequestMapping("office/findUser")
    @ResponseBody
    public User findUser(User user) throws Exception {
    return userService.get(user.getUserId());
    }








}
