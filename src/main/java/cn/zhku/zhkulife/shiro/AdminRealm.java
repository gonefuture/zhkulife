package cn.zhku.zhkulife.shiro;

import cn.zhku.zhkulife.modules.admin.service.AdminService;
import cn.zhku.zhkulife.po.entity.Admin;
import cn.zhku.zhkulife.po.entity.Authority;
import cn.zhku.zhkulife.po.entity.Role;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/7/29 19:37.
 * @E-mail gonefuture@qq.com
 */
public class AdminRealm extends AuthorizingRealm {
        // 用户对应的角色信息与权限信息都保存在数据库中，通过UserService获取数据
        @Autowired
        private ShiroService shiroService ;

        /**
         * 提供用户信息返回权限信息
         */
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
            String adminId = (String) principals.getPrimaryPrincipal();
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            // 根据用户名查询当前用户拥有的角色
            Set<String> roleIdSet = shiroService.findRoleIds(adminId);
            System.out.println(roleIdSet);
            // 将角色名称提供给info
            authorizationInfo.setRoles(roleIdSet);

            // 根据用户名查询当前用户权限
            Set<String> authorityIdsSet = shiroService.findAuthorities(roleIdSet);
            System.out.println(authorityIdsSet);
            // 将权限名称提供给info
            authorizationInfo.setStringPermissions(authorityIdsSet);

            return authorizationInfo;
        }

        /**
         * 提供账户信息返回认证信息
         */
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
            String adminId = (String) token.getPrincipal();
            Admin admin =shiroService.findByAdminId(adminId);
            if (admin == null) {
                // 用户名不存在抛出异常
                throw new UnknownAccountException();
            }

            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin.getAdminId(),
                    admin.getAdminPassword(), getName());
            return authenticationInfo;
        }

        public ShiroService getShiroService() {
            return shiroService;
        }

        public void setShiroService(ShiroService shiroService) {
            this.shiroService = shiroService;
        }
}

