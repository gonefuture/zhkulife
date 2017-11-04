package cn.zhku.zhkulife.modules.user.service;

import cn.zhku.zhkulife.po.entity.User;
import cn.zhku.zhkulife.po.entity.UserExample;
import cn.zhku.zhkulife.po.entity.Water;
import cn.zhku.zhkulife.po.mapper.UserMapper;
import cn.zhku.zhkulife.utils.Beans.CommonQo;
import cn.zhku.zhkulife.utils.interfaceUtils.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 22:42.
 * @E-mail gonefuture@qq.com
 */

@Service
public class UserService implements IService<User>{
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    /**
     *  普通用户登录
     * @param form 必须：userId password
     * @return  User实体
     * @throws Exception    sqlException
     */
    public User login(User form) throws Exception{
        User user =this.get(form.getUserId());

        if(user != null && user.getUserPassword().equals(form.getUserPassword()))
            return user;
        else
            return null;
    }


    @Override
    public int add(User entity) throws Exception {
        return userMapper.insert(entity);
    }

    @Override
    public int update(User entity) throws Exception {
        return userMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int delete(User entity) throws Exception {
        return userMapper.deleteByPrimaryKey(entity.getUserId());
    }

    @Override
    public User get(String id) throws Exception {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getList(User entity) throws Exception {
        return null;
    }


    /**
     *  多添加查找用户
     * @param entity    参数：userZone ,totalWater, userPhone
     * @return
     * @throws Exception
     */
    @Override
    public List<User> findAll(User entity) throws Exception {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (entity.getUserZone() != null)
            criteria.andUserZoneEqualTo(entity.getUserZone());
        if (entity.getTotalWater() != null)
            criteria.andTotalWaterGreaterThanOrEqualTo(entity.getTotalWater());
        if(entity.getUserPhone() != null) {
            criteria.andUserPhoneEqualTo(entity.getUserPhone());
        }
        if (entity.getUserId()!= null) {
            criteria.andUserIdEqualTo(entity.getUserId());
        }
        return userMapper.selectByExample(null);
    }
}
