package cn.zhku.zhkulife.modules.admin.service;


import cn.zhku.zhkulife.modules.role.service.AdminRoleService;
import cn.zhku.zhkulife.po.entity.Admin;
import cn.zhku.zhkulife.po.entity.AdminExample;
import cn.zhku.zhkulife.po.entity.AdminRole;
import cn.zhku.zhkulife.po.entity.Water;
import cn.zhku.zhkulife.po.mapper.AdminMapper;


import cn.zhku.zhkulife.utils.Beans.CommonQo;
import cn.zhku.zhkulife.utils.interfaceUtils.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 钱伟健 gonefutre
 * @date 2017/7/29 19:14.
 * @E-mail gonefuture@qq.com
 */
@Service
public class AdminService implements IService<Admin>{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminRoleService adminRoleService;



    @Override
    public int add(Admin entity) throws Exception {
        return adminMapper.insert(entity);
    }

    @Override
    public int update(Admin entity) throws Exception {
        return adminMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int delete(Admin entity) throws Exception {
        return adminMapper.deleteByPrimaryKey(entity.getAdminId());
    }

    @Override
    public Admin get(String id) throws Exception {
       return adminMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<Admin> getList(Admin entity) throws Exception {
        AdminExample adminExample = new AdminExample();
        adminExample.or().andAdminRoleEqualTo(entity.getAdminRole());
        return adminMapper.selectByExample(adminExample);
    }

    @Override
    public List<Admin> findAll(Admin entity) throws Exception {
        return adminMapper.selectByExample(new AdminExample());
    }

    /**
     *  管理员登陆服务，返回Admin
     * @param form  必须参数: AdminId password
     * @return   Admin
     * @throws Exception sql
     */
    public Admin login(Admin form) throws Exception {
        Admin admin =this.get(form.getAdminId());
        if(admin != null && admin.getAdminPassword().equals(form.getAdminPassword()))
            return admin;
        else
           return  null;
    }

    /**
     *  增加一个管理员并添加其对应的权限
     * @param form
     * @param adminCache
     * @return
     * @throws Exception
     */
    public boolean addRole(Admin form, Admin adminCache) throws Exception {
        int operatorRole = Integer.valueOf(adminCache.getAdminRole());
        AdminRole adminRole = new AdminRole();

        switch (operatorRole) {
            case 4:
                form.setAdminRole("2"); adminRole.setAdminId(form.getAdminId()); adminRole.setRoleId("2");
                this.add(form);
                return adminRoleService.addRoleByRole6(form);

            case 5:
                form.setAdminRole("3"); adminRole.setAdminId(form.getAdminId()); adminRole.setRoleId("3");
                this.add(form);
                return adminRoleService.addRoleByRole6(form);

            case 6:
                this.add(form);
                return adminRoleService.addRoleByRole6(form);
            default:
                return false;
        }

    }




    /**
     *  移除管理员及其权限
     * @param adminId
     * @return
     * @throws Exception
     */
    public boolean removeAdmin(String adminId) throws Exception {
        Admin admin = new Admin(); admin.setAdminId(adminId);

        AdminRole adminRoleEntity = new AdminRole();
        adminRoleEntity.setAdminId(adminId);
        if (this.delete(admin) == 1) {
            adminRoleService.delete(adminRoleEntity);
            return true;
        }
        else
            return false;
    }

    /**
     *  修改管理员信息，无法修改权限
     * @param form
     * @param adminCache
     * @return
     * @throws Exception
     */
    public boolean modifyRole(Admin form, Admin adminCache) throws Exception {
        form.setAdminRole(null);    //  使得管理员的权限无法修改
        if(this.update(form) == 1)
            return true;
        else
            return false;
    }
}
