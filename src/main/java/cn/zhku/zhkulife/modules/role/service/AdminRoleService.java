package cn.zhku.zhkulife.modules.role.service;

import cn.zhku.zhkulife.modules.role.dao.RoleDao;
import cn.zhku.zhkulife.po.entity.Admin;
import cn.zhku.zhkulife.po.entity.AdminRole;
import cn.zhku.zhkulife.po.entity.AdminRoleExample;
import cn.zhku.zhkulife.po.entity.Water;
import cn.zhku.zhkulife.po.mapper.AdminRoleMapper;
import cn.zhku.zhkulife.utils.Beans.CommonQo;
import cn.zhku.zhkulife.utils.interfaceUtils.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/9/1 20:28.
 * @E-mail gonefuture@qq.com
 */
@Service
public class AdminRoleService implements IService<AdminRole> {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    AdminRoleMapper adminRoleMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    RoleDao roleDao;

    @Override
    public int add(AdminRole entity) throws Exception {
        return adminRoleMapper.insert(entity);
    }

    @Override
    public int update(AdminRole entity) throws Exception {
        return 0;
    }

    /**
     *  根据adminId删除指定管理员的所有权限
     * @param adminRole 用户权限
     *
     * @return 影响的记录数
     * @throws Exception  sqlException
     */
    @Override
    public int delete(AdminRole adminRole) throws Exception {
        AdminRoleExample adminRoleExample = new AdminRoleExample();
        AdminRoleExample.Criteria criteria = adminRoleExample.createCriteria();
        criteria.andAdminIdEqualTo(adminRole.getAdminId());   //根据adminId删除指定的所有权限
        return adminRoleMapper.deleteByExample(adminRoleExample);
    }

    //未启用
    @Override
    public AdminRole get(String id) throws Exception {
        return null;
    }

    //未启用
    @Override
    public List<AdminRole> getList(AdminRole entity) throws Exception {
        return null;
    }

    //未启用
    @Override
    public List<AdminRole> findAll(AdminRole entity) throws Exception {
        return null;
    }

    /**
     *  通过权限6来给其他管理员添加权限
     * @param form 需要添加权限的管理员。
     */
    public boolean addRoleByRole6(Admin form) {
        int wantRole = Integer.valueOf(form.getAdminRole());
        switch (wantRole) {
            case 4:
                if(roleDao.addRole4(form.getAdminId()) == 2)
                    return true;
                else
                    return false;
            case 5:
                if(roleDao.addRole5(form.getAdminId()) == 2)
                    return true;
                else
                    return false;
            case 6:
                if(roleDao.addRole6(form.getAdminId()) == 5)
                    return true;
                else
                    return false;
            default:
                return false;
        }

    }
}
