package cn.zhku.zhkulife.modules.repair.service;

import cn.zhku.zhkulife.po.entity.Repair;
import cn.zhku.zhkulife.po.entity.RepairExample;
import cn.zhku.zhkulife.po.entity.Water;
import cn.zhku.zhkulife.po.mapper.RepairMapper;
import cn.zhku.zhkulife.utils.Beans.CommonQo;
import cn.zhku.zhkulife.utils.interfaceUtils.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 22:46.
 * @E-mail gonefuture@qq.com
 */
@Service
public class RepairService implements IService<Repair> {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    RepairMapper repairMapper;


    @Override
    public int add(Repair entity ) throws Exception {
        return  repairMapper.insert(entity);
    }

    @Override
    public int update(Repair entity) throws Exception {
        return repairMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int delete(Repair entity) throws Exception {
        return repairMapper.deleteByPrimaryKey(entity.getRepairId());
    }

    @Override
    public Repair get(String id) throws Exception {
        return repairMapper.selectByPrimaryKey(id);
    }

    /**     普通用户查找订单
     *
     * @param repair  参数: repairState
     * @return   repair列表
     * @throws Exception    sqlException
     */
    @Override
    public List<Repair> getList(Repair repair) throws Exception {
        RepairExample repairExample = new RepairExample();
        RepairExample.Criteria criteria = repairExample.createCriteria();
        if (repair.getRepairState() != null)
            criteria.andRepairStateEqualTo(repair.getRepairState());
        criteria.andUserIdEqualTo(repair.getUserId());
        return repairMapper.selectByExampleWithBLOBs(repairExample);
    }

    @Override
    public List<Repair> findAll(Repair entity) throws Exception {
        return null;
    }

    /**     多条件查询维修订单
     *
     * @param commonQo
     * @param repair
     * @return
     * @throws Exception
     */
    public List<Repair> findAll(CommonQo commonQo, Repair repair) throws Exception {
        RepairExample repairExample = new RepairExample();
        RepairExample.Criteria criteria = repairExample.createCriteria();
        if (repair.getUserId() != null)
            criteria.andUserIdEqualTo(repair.getUserId());
        if (repair.getRepairState() != null)
            criteria.andRepairStateEqualTo(repair.getRepairState());
        if (repair.getZone() != null)
            criteria.andZoneEqualTo(repair.getZone());
        if (repair.getRepairFeedback() != null)
            criteria.andRepairFeedbackEqualTo(repair.getRepairFeedback());
        if (repair.getRepairId() != null)
            criteria.andAdminIdEqualTo(repair.getAdminId());
        if (repair.getRepairFeedback() != null)
            criteria.andRepairStateEqualTo(repair.getRepairState());
        if (commonQo.getSinceTime() != null)
            criteria.andRepairTimeGreaterThanOrEqualTo(commonQo.getSinceTime());  //按时间查询
        if (commonQo.getEndTime() != null)
            criteria.andRepairTimeLessThanOrEqualTo(commonQo.getEndTime());

        return repairMapper.selectByExampleWithBLOBs(repairExample);
    }

    /**
     *  查找维修评价
     * @param commonQo  分页和时间查询
     * @return  订单列表
     */
    public List<Repair> repined(CommonQo commonQo) {
        RepairExample repairExample = new RepairExample();
        RepairExample.Criteria criteria = repairExample.createCriteria();
        criteria.andRepairFeedbackNotEqualTo(5);
        criteria.andRepairFeedbackIsNotNull();
        if (commonQo.getSinceTime() != null)
            criteria.andRepairTimeGreaterThanOrEqualTo(commonQo.getSinceTime());  //按时间查询
        if (commonQo.getEndTime() != null)
            criteria.andRepairTimeLessThanOrEqualTo(commonQo.getEndTime());
        return  repairMapper.selectByExampleWithBLOBs(repairExample);
    }
}
