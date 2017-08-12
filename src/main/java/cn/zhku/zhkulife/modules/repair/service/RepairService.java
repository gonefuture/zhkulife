package cn.zhku.zhkulife.modules.repair.service;

import cn.zhku.zhkulife.po.entity.Repair;
import cn.zhku.zhkulife.po.entity.RepairExample;
import cn.zhku.zhkulife.po.mapper.RepairMapper;
import cn.zhku.zhkulife.utils.interfaceUtils.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return repairMapper.updateByPrimaryKeySelective(null);
    }

    @Override
    public int delete(Repair entity) throws Exception {
        return repairMapper.deleteByPrimaryKey(entity.getRepairId());
    }

    @Override
    public Repair get(String id) throws Exception {
        return repairMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Repair> getList(Repair entity) throws Exception {
        return null;
    }

    @Override
    public List<Repair> findAll(Repair repair) throws Exception {
        RepairExample repairExample = new RepairExample();
        repairExample.setOrderByClause("repair_time desc");
        RepairExample.Criteria criteria = repairExample.createCriteria();
        if (repair.getUserId() != null)
            criteria.andUserIdEqualTo(repair.getUserId());
        if (repair.getZone() != null)
            criteria.andZoneEqualTo(repair.getZone());
        if (repair.getRepairFeedback() != null)
            criteria.andRepairFeedbackEqualTo(repair.getRepairFeedback());
        if (repair.getRepairId() != null)
            criteria.andAdminIdEqualTo(repair.getAdminId());
        if (repair.getRepairFeedback() != null)
            criteria.andRepairStateEqualTo(repair.getRepairState());

        return repairMapper.selectByExample(repairExample);
    }


}
