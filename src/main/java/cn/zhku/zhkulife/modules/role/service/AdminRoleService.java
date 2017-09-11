package cn.zhku.zhkulife.modules.role.service;

import cn.zhku.zhkulife.po.entity.AdminRole;
import cn.zhku.zhkulife.po.entity.AdminRoleExample;
import cn.zhku.zhkulife.po.mapper.AdminRoleMapper;
import cn.zhku.zhkulife.utils.interfaceUtils.IBaseService;
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

    @Override
    public int add(AdminRole entity) throws Exception {
        return adminRoleMapper.insert(entity);
    }

    @Override
    public int update(AdminRole entity) throws Exception {
        return 0;
    }

    @Override
    public int delete(AdminRole entity) throws Exception {
        AdminRoleExample adminRoleExample = new AdminRoleExample();
        AdminRoleExample.Criteria criteria = adminRoleExample.createCriteria();
        criteria.andAdminIdEqualTo(entity.getAdminId());
        criteria.andRoleIdEqualTo(entity.getRoleId());
        return adminRoleMapper.deleteByExample(adminRoleExample);
    }

    @Override
    public AdminRole get(String id) throws Exception {
        return null;
    }

    @Override
    public List<AdminRole> getList(AdminRole entity) throws Exception {
        return null;
    }

    @Override
    public List<AdminRole> findAll(AdminRole entity) throws Exception {
        return null;
    }
}
