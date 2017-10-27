package cn.zhku.zhkulife.shiro;

import cn.zhku.zhkulife.po.entity.*;
import cn.zhku.zhkulife.po.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 10:29.
 * @E-mail gonefuture@qq.com
 */
@Service
public class ShiroService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    AdminMapper adminMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    RoleAuthorityMapper roleAuthorityMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    AdminRoleMapper adminRoleMapper;


    /**
     *  查找登录管理员的角色
     * @param adminId   管理员id
     *  @return 角色集合
     */
    public Set<String> findRoleIds(String adminId) {
        Set<String> roleIdsSet = new HashSet<String>();
        AdminRoleExample adminRoleExample = new AdminRoleExample();
        adminRoleExample.or().andAdminIdEqualTo(adminId);
        List<AdminRole> adminRoleList = adminRoleMapper.selectByExample(adminRoleExample);
        for(AdminRole adminRole : adminRoleList) {
            roleIdsSet.add(adminRole.getRoleId());

        }
        System.out.println("============ 权限集合: "+roleIdsSet+"    ==========");
        return roleIdsSet;
    }


    /**
     *  查找登录管理员的所有权限
     * @param roleIds   管理员的角色集合
     * @return  权限集合
     */
    public Set<String> findAuthorities(Set<String> roleIds) {
        Set<String> authorities = new HashSet<String>();
        for (String roleId : roleIds) {
            RoleAuthorityExample roleAuthorityExample = new RoleAuthorityExample();
            roleAuthorityExample.or().andRoleIdEqualTo(roleId);
            List<RoleAuthority> roleAuthorities = roleAuthorityMapper.selectByExample(roleAuthorityExample);
            for (RoleAuthority roleAuthority: roleAuthorities ) {
                authorities.add(roleAuthority.getAuthorityId());
            }

        }
        return authorities;
    }

    /**
     *  通过管理呀员查找管理员
     * @param adminId   管理员id
     * @return
     */
     public Admin findByAdminId(String adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }
}
