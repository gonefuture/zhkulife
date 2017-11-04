package cn.zhku.zhkulife.modules.admin.controller;

import cn.zhku.zhkulife.modules.admin.service.AdminService;
import cn.zhku.zhkulife.modules.role.service.AdminRoleService;
import cn.zhku.zhkulife.po.entity.Admin;
import cn.zhku.zhkulife.po.entity.AdminRole;
import cn.zhku.zhkulife.utils.Beans.Message;
import cn.zhku.zhkulife.utils.Beans.CommonQo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

    /**     添加管理员并添加其权限
     * @param form
     * @return
     */
    @RequestMapping("admin/addRole")
    @ResponseBody
    public Message addRole(Admin form)  {
        Admin adminCache = (Admin) SecurityUtils.getSubject().getSession().getAttribute("admin");  //获取缓存的管理员信息         //创建管理员-权限关系实体
        try {
            if(adminService.addRole(form,adminCache)){
                return new Message("2","添加管理员成功");
            }
            else {
                return new Message("2","添加管理员失败,你的权限不足");
            }
        }catch (DuplicateKeyException duplicateKeyException) {
            return new Message("2","管理员已存在，请不要重复添加");
        } catch (Exception e) {
            return new Message("2","出现其他错误了。请联系开发者");
        }
    }



    /**     根据adminId删除管理员并连同其所有角色删除
     *
     * @param adminId   需要删除的管理员
     * @return  Message实体
     * @throws Exception   sql
     */
    @RequestMapping("admin/removeRole")
    @ResponseBody
    public Message delete(String adminId) throws Exception {

        if (adminService.removeAdmin(adminId))
            return new Message("1","删除管理员成功");
        else
            return new Message("2","删除管理员失败,你的权限不足");
    }



    /**     根据adminId修改删除管理员
     *
     * @param form  必须：adminId ，注意：不可带上adminRole
     * @return  Message实体
     * @throws Exception  sql
     */
    @RequestMapping("admin/modifyRole")
    @ResponseBody
    public Message modifyRole(Admin form) throws Exception {
        Admin adminCache = (Admin) SecurityUtils.getSubject().getSession().getAttribute("admin");
        if(adminService.modifyRole(form,adminCache)){
            return new Message("2","修改管理员成功");
        }
        else {
            return new Message("2","修改管理员失败,你的权限不足");
        }

    }





    /**     根据自身寻找权限
     *
     * @param query  公共查询类
     * @return  PageInfo 类
     * @throws Exception  sql
     */
    @RequestMapping("admin/findRole")
    @ResponseBody
    public PageInfo<Admin> findRole(CommonQo query) throws Exception {
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Subject subject = SecurityUtils.getSubject();
        Admin adminCache = (Admin) subject.getSession().getAttribute("admin");
        Admin adminQo = new Admin();

        if (adminCache.getAdminRole().equals("6"))
            return new PageInfo<Admin>(adminService.findAll( null));
        else{
            if (adminCache.getAdminRole().equals("4")){
                adminQo.setAdminRole("2");
                return new PageInfo<Admin>(adminService.getList(adminQo));
            } else {
                adminQo.setAdminRole("3");
                return new PageInfo<Admin>(adminService.getList(adminQo));
            }

        }
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
                return new Message("3", "管理员未设置手机号码，请设置手机号码。（必须）", admin.getAdminRole(),admin.getAdminZone());
            else
                return new Message("1", "管理员登录成功", admin.getAdminRole(),admin.getAdminZone());
        }
    }

    /**
     * 修改用户密码
     * 管理员进行密码修改,角色6管理员可以对角色4和5的密码进行修改
     * 当adminId属性为空的时候,则默认为角色6对自己的密码进行修改
     * 当adminId属性不为空的时候,则认为是角色6对角色4或者5的密码进行修改
     * @param form  必须要参数adminPassword, adminId可选
     * @return Message信息类
     * @throws Exception
     */
    @RequestMapping("admin/updatePassword")
    @ResponseBody
    public Message updatePassword(Admin form) throws Exception {
        Admin admin = new Admin(); //创建一个接受参数的对象，这样用户只能修改密码，而不能修改其他东西。
        if(form.getAdminId() == null) {     //如果请求中没有提交adminid，从session中获取
            Admin adminCache  = (Admin) SecurityUtils.getSubject().getSession().getAttribute("admin"); //获取session中的admin
            admin.setAdminId(adminCache.getAdminId());
            admin.setAdminPassword(form.getAdminPassword());
        } else {
            //如果请求中提交了adminid
            admin.setAdminId(form.getAdminId());
            admin.setAdminPassword(form.getAdminPassword());
        }

        if (adminService.update(admin) != 1)
            return new Message("2","修改密码失败，请检查参数");
        else
            return new Message("1","修改密码成功");

    }


    /**修改手机
     * 管理员进行手机修改,角色6管理员可以对角色4和5的手机进行修改
     * 当adminId属性为空的时候,则默认为角色6对自己的手机进行修改
     * 当adminId属性不为空的时候,则认为是角色6对角色4或者5的手机进行修改
     * @param form 必须要参数adminPhone, adminId可选
     * @return  Message信息类
     * @throws Exception
     */
    @RequestMapping("admin/updatePhone")
    @ResponseBody
    public Message updatePhone(Admin form) throws Exception {
        Admin admin = new Admin();
        if (form.getAdminId() == null){
            Admin adminCache  = (Admin) SecurityUtils.getSubject().getSession().getAttribute("admin"); //获取session中的admin
            admin.setAdminId(adminCache.getAdminId());
            admin.setAdminPhone(form.getAdminPhone());
        } else {
            admin.setAdminId(form.getAdminId());
            admin.setAdminPhone(form.getAdminPhone());
        }
        if (adminService.update(admin) != 1)
            return new Message("2","修改手机号码失败");
        else
            return new Message("1","修改手机号码成功");
    }

}
