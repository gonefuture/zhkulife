package cn.zhku.zhkulife.po.mapper;

import cn.zhku.zhkulife.po.entity.Water;
import cn.zhku.zhkulife.po.entity.WaterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface WaterMapper {

    @Select("select water_id, water_num, operate_time, water_time, water_state, user_id, admin_id, water_feedback, water_count, zone, admin_phone, user_phone from water WHERE ( user_id = #{userId} ) and( water_state =1 or water_state =2 or water_state=3)")
    List<Water> isHasBook(@Param("userId")String userId);

    int countByExample(WaterExample example);

    int deleteByExample(WaterExample example);

    int deleteByPrimaryKey(String waterId);

    int insert(Water record);

    int insertSelective(Water record);

    List<Water> selectByExample(WaterExample example);

    Water selectByPrimaryKey(String waterId);

    int updateByExampleSelective(@Param("record") Water record, @Param("example") WaterExample example);

    int updateByExample(@Param("record") Water record, @Param("example") WaterExample example);

    int updateByPrimaryKeySelective(Water record);

    int updateByPrimaryKey(Water record);

}