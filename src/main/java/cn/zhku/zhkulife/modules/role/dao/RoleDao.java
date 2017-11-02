package cn.zhku.zhkulife.modules.role.dao;

import org.apache.ibatis.annotations.Insert;

/**
 * @author : 钱伟健 gonefuture@qq.com
 * @version : 2017/10/27 9:53.
 * 说明：关于权限的定制sql
 */
public interface RoleDao {

    /**
     *  添加权限6
      * @param id
     * @return  改变记录的条数
     */
    @Insert("INSERT INTO admin_role VALUES (#{id},2),(#{id},3),(#{id},4),(#{id},5),(#{id},6);")
    int addRole6(String id);


    /**
     *  添加权限4
     * @param id
     * @return  改变记录的条数
     */
    @Insert("INSERT INTO admin_role VALUES (#{id},2),(#{id},4);")
    int addRole4(String id);

    /**
     *  添加权限5
     * @param id
     * @return  改变记录的条数
     */
    @Insert("INSERT INTO admin_role VALUES (#{id},3),(#{id},5);")
    int addRole5(String id);
}
