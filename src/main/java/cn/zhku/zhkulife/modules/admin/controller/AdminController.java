package cn.zhku.zhkulife.modules.admin.controller;

import cn.zhku.zhkulife.modules.admin.service.AdminService;
import cn.zhku.zhkulife.modules.role.service.AdminRoleService;
import cn.zhku.zhkulife.po.entity.Admin;
import cn.zhku.zhkulife.po.entity.AdminRole;
import cn.zhku.zhkulife.utils.Beans.Message;
import cn.zhku.zhkulife.utils.Beans.Query;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 10:08.
 * @E-mail gonefuture@qq.com
 */
@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    AdminRoleService adminRoleService;

    @RequestMapping("admin/addRole")
    @ResponseBody
    public Message addRole(Admin form) throws Exception {

        if (addAdmin(form,"4","2")) {
            return new Message("1","添加送水师傅成功");
        }
        if ( addAdmin(form,"5","3"))
            return new Message("1","添加维修师傅成功");
        if (addAdmin(form,"6",null))
            return new Message("1","添加管理员成功");
        else
            return new Message("2","添加管理员失败,你的权限不足");
    }

    public boolean addAdmin (Admin form,String adminRole,String changeRole) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Admin adminCache = (Admin) subject.getSession().getAttribute("admin");
        AdminRole adminRoleEntity = new AdminRole();
        adminRoleEntity.setAdminId(form.getAdminId()); adminRoleEntity.setRoleId(changeRole);
        if (changeRole != null)
            form.setAdminRole(changeRole);
        if ( adminCache.getAdminRole().equals(adminRole) && adminService.add(form) == 1 ){
            adminRoleService.add(adminRoleEntity);
            return true;
        }
        else
            return false;
    }



    @RequestMapping("admin/removeRole")
    @ResponseBody
    public Message delete(Admin form) throws Exception {
        if (removeAdmin(form,"4","2")) {
            return new Message("1","删除送水师傅成功");
        }
        if ( removeAdmin(form,"5","3"))
            return new Message("1","删除维修师傅成功");
        if (removeAdmin(form,"6",null))
            return new Message("1","删除管理员成功");
        else
            return new Message("2","删除管理员失败,你的权限不足");
    }

    public boolean removeAdmin(Admin form,String adminRole,String changeRole) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Admin adminCache = (Admin) subject.getSession().getAttribute("admin");
        AdminRole adminRoleEntity = new AdminRole();
        adminRoleEntity.setAdminId(form.getAdminId()); adminRoleEntity.setRoleId(changeRole);

        if ( adminCache.getAdminRole().equals(adminRole) &&adminService.delete(form) == 1) {
                adminRoleService.delete(adminRoleEntity);
                return true;
        }
        else
            return false;
    }


    @RequestMapping("admin/modifyRole")
    @ResponseBody
    public Message modifyRole(Admin form) throws Exception {
        if (modifyAdmin(form,"4","2")) {
            return new Message("1","修改送水师傅信息成功");
        }
        if ( modifyAdmin(form,"5","3"))
            return new Message("1","修改维修师傅信息成功");
        if (modifyAdmin(form,"6",null))
            return new Message("1","修改管理员成功");
        else
            return new Message("2","修改管理员失败,你的权限不足");
    }


    public boolean modifyAdmin(Admin form,String adminRole,String changeRole) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Admin adminCache = (Admin) subject.getSession().getAttribute("admin");
        if ( adminCache.getAdminRole().equals(adminRole) &&adminService.update(form) == 1)
            return true;
        else
            return false;
    }






    @RequestMapping("admin/findRole")
    @ResponseBody
    public PageInfo<Admin> findRole(Query query) throws Exception {
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Subject subject = SecurityUtils.getSubject();
        Admin adminCache = (Admin) subject.getSession().getAttribute("admin");
        Admin adminQo = new Admin ();
        adminQo.setAdminRole(adminCache.getAdminRole());
        if (adminQo.getAdminRole().equals("6"))
            return new PageInfo<Admin>(adminService.findAll(null));
        else{
            if (adminQo.getAdminRole().equals("4")){
                adminQo.setAdminRole("2");
                return new PageInfo<Admin>(adminService.getList(adminQo));
            } else{
                adminQo.setAdminRole("3");
                return new PageInfo<Admin>(adminService.getList(adminQo));
            }

        }
    }




//    @RequestMapping("admin/get")
//    @ResponseBody
//    public Admin get( Admin admin) throws Exception {
//        return  adminService.get(admin.getAdminId());
//    }

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
                return new Message("3", "管理员未设置手机号码，请设置手机号码。（必须）", admin.getAdminRole(),admin.getAdminZone());
            else
                return new Message("1", "管理员登录成功", admin.getAdminRole(),admin.getAdminZone());
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
        System.out.println("-----------------------------"+phone);
        Admin admin = new Admin();
        admin.setAdminId(adminId); admin.setAdminPhone(phone);
        if (adminService.update(admin) != 1)
            return new Message("2","修改手机号码失败");
        else
            return new Message("1","修改手机号码成功");
    }

}
