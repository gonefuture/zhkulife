package cn.zhku.zhkulife.modules.admin.controller;

import cn.zhku.zhkulife.modules.admin.service.AdminService;
import cn.zhku.zhkulife.po.entity.Admin;
import cn.zhku.zhkulife.utils.Beans.Message;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 10:08.
 * @E-mail gonefuture@qq.com
 */
@Controller

public class AdminController {
    @Autowired
    AdminService adminService;



    @RequestMapping("admin/add")
    @ResponseBody
    public Message add(Admin admin) throws Exception {
        if (adminService.add(admin) >0)
            return new Message("1","管理员增加成功");
        else
            return new Message("2","管理员增加失败");
    }

    @RequestMapping("admin/delete")
    @ResponseBody
    public Message delete(Admin admin) throws Exception {
        if (adminService.delete(admin) >0)
            return new Message("1","管理员删除成功");
        else
            return new Message("2","管理删除失败");
    }

    @RequestMapping("admin/edit")
    @ResponseBody
    public Message edit(Admin admin) throws Exception {
        if (adminService.update(admin) >0)
            return new Message("1","管理员修改成功");
        else
            return new Message("2","管理修改失败");
    }

    @RequestMapping("admin/list")
    @ResponseBody
    public List<Admin> list() throws Exception {
        return adminService.findAll(null);

    }

    @RequestMapping("admin/get")
    @ResponseBody
    public Admin get( Admin admin) throws Exception {
        return  adminService.get(admin.getAdminId());
    }

    @RequestMapping("/login/admin")
    @ResponseBody
    public Message login(Admin form) throws Exception {
        UsernamePasswordToken token = new UsernamePasswordToken(form.getAdminId(), form.getAdminPassword());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        token.setRememberMe(true);
        Admin admin = adminService.login(form);
        if (admin == null)
            return new Message("2", "管理员不存在或密码错误，请重新登录");
        else {
            subject.login(token);
            session.setAttribute( "admin", admin );
            if (admin.getAdminPhone() == null || admin.getAdminPhone().trim().isEmpty())
                return new Message("3", "管理员未设置手机号码，请设置手机号码。（必须）", admin.getAdminRole());
            else
                return new Message("1", "管理员登录成功", admin.getAdminRole());
        }
    }

    @RequestMapping("admin/updatePassword")
    @ResponseBody
    public Message updatePassword(String adminId,String password) throws Exception {
        Admin admin = new Admin();
        admin.setAdminId(adminId); admin.setAdminPassword(password);
        if (adminService.update(admin) != 1)
            return new Message("2","修改密码失败，请检查参数");
        else
            return new Message("1","修改密码成功");

    }

    @RequestMapping("admin/updatePhone")
    @ResponseBody
    public Message updatePhone(String adminId,String phone) throws Exception {
        Admin admin = new Admin();
        admin.setAdminId(adminId); admin.setAdminPhone(phone);
        if (adminService.update(admin) != 1)
            return new Message("2","修改手机号码失败");
        else
            return new Message("1","修改手机号码成功");
    }

}
