package cn.zhku.zhkulife.modules.water.service;

import cn.zhku.zhkulife.po.entity.Water;
import cn.zhku.zhkulife.po.entity.WaterExample;
import cn.zhku.zhkulife.po.mapper.WaterMapper;
import cn.zhku.zhkulife.utils.interfaceUtils.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 22:45.
 * @E-mail gonefuture@qq.com
 */
@Service
public class WaterService implements IService<Water>{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private WaterMapper waterMapper;


    @Override
    public int add(Water entity) throws Exception {
        return waterMapper.insert(entity);
    }

    @Override
    public int update(Water entity) throws Exception {
        return waterMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int delete(Water entity) throws Exception {
        return waterMapper.deleteByPrimaryKey(entity.getWaterId());
    }

    @Override
    public Water get(String id) throws Exception {
        return waterMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Water> getList(Water water) throws Exception {
        WaterExample waterExample = new WaterExample();
        waterExample.setOrderByClause("water_time desc");
        WaterExample.Criteria criteria = waterExample.createCriteria();
        if (water.getWaterState() != null)
            criteria.andWaterStateEqualTo(water.getWaterState());
        criteria.andUserIdEqualTo(water.getUserId());
        return waterMapper.selectByExample(waterExample);
    }

    @Override
    public List<Water> findAll(Water water) throws Exception {
        WaterExample waterExample = new WaterExample();
        waterExample.setOrderByClause("water_time desc");
        WaterExample.Criteria criteria = waterExample.createCriteria();
        if(water.getUserId() != null)
            criteria.andUserIdEqualTo(water.getUserId());
        if(water.getAdminId() != null)
            criteria.andAdminIdEqualTo(water.getAdminId());
        if (water.getWaterState() != null)
            criteria.andWaterStateEqualTo(water.getWaterState());
        if(water.getWaterFeedback() != null)
            criteria.andWaterFeedbackEqualTo(water.getWaterFeedback());
        if(water.getZone() != null)
            criteria.andZoneEqualTo(water.getZone());

        return waterMapper.selectByExample(waterExample);
    }



    public boolean isHasBook(Water water) {
        WaterExample waterExample = new WaterExample();
        waterExample.or().andWaterStateEqualTo(1);
        waterExample.or().andWaterStateEqualTo(2);
        List<Water> waterList =waterMapper.selectByExample(waterExample);
        if (waterList.size()>0)
            return true;
        else
            return false;
    }
}
