package cn.zhku.zhkulife.modules.water.service;

import cn.zhku.zhkulife.po.dao.WaterDao;
import cn.zhku.zhkulife.po.entity.Water;
import cn.zhku.zhkulife.po.entity.WaterExample;
import cn.zhku.zhkulife.po.mapper.WaterMapper;
import cn.zhku.zhkulife.utils.Beans.CommonQo;
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

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private WaterDao waterDao;


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

    /**     通过userId和waterState查找 订单
     *
     * @param water   参数：userId waterState
     * @return
     * @throws Exception
     */
    @Override
    public List<Water> getList(Water water) throws Exception {
        WaterExample waterExample = new WaterExample();
        WaterExample.Criteria criteria = waterExample.createCriteria();
        if (water.getWaterState() != null)
            criteria.andWaterStateEqualTo(water.getWaterState());
        criteria.andUserIdEqualTo(water.getUserId());
        return waterMapper.selectByExample(waterExample);
    }

    @Override
    public List<Water> findAll(Water enty) throws Exception {
        return null;
    }

    /**     多条件查询
     *
     * @param commonQo  通用查询类
     * @param water
     * @return
     * @throws Exception
     */
    public List<Water> findAll(CommonQo commonQo, Water water) throws Exception {
        WaterExample waterExample = new WaterExample();
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
        if (commonQo.getSince()!= null )
            criteria.andOperateTimeGreaterThanOrEqualTo(commonQo.getSince());
        if (commonQo.getEnd() != null)
            criteria.andOperateTimeLessThan(commonQo.getEnd());

        return waterMapper.selectByExample(waterExample);
    }


    public boolean isHasBook(Water water) {


        List<Water> waterList =waterDao.isHasBook(water.getUserId());
        return waterList.size() > 0;
    }
}
