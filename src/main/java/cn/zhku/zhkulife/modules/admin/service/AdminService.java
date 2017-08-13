package cn.zhku.zhkulife.modules.admin.service;


import cn.zhku.zhkulife.po.entity.Admin;
import cn.zhku.zhkulife.po.entity.AdminExample;
import cn.zhku.zhkulife.po.mapper.AdminMapper;


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
        return adminMapper.updateByPrimaryKey(entity);
    }

    @Override
    public Admin get(String id) throws Exception {
       return adminMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<Admin> getList(Admin entity) throws Exception {
        AdminExample adminExample = new AdminExample();
        return adminMapper.selectByExample(adminExample);
    }

    @Override
    public List<Admin> findAll(Admin entity) throws Exception {
        return adminMapper.selectByExample(new AdminExample());
    }


    public Admin login(Admin form) throws Exception {
        Admin admin =this.get(form.getAdminId());
        if(admin != null && admin.getAdminPassword().equals(form.getAdminPassword()))
            return admin;
        else
           return  null;
    }
}
