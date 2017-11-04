package cn.zhku.zhkulife.po.dao;

import cn.zhku.zhkulife.po.entity.Water;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

/** @author 钱伟健 gonefutre
 * @date 2017/9/13 21:10.
 * @E-mail gonefuture@qq.com
 */
public interface WaterDao {
    /**
     *  查询用户是否已经订水
     * @param userId
     * @return
     */
    @Select("select water_id, water_num, operate_time, water_time, water_state, user_id, admin_id, water_feedback, water_count, zone, admin_phone, user_phone from water WHERE ( user_id = #{userId} ) and( water_state =1 or water_state =2 or water_state=3)")
    List<Water> isHasBook(@Param("userId") String userId);

    @Update("UPDATE `user` SET total_water = (user.total_water + (SELECT water_num FROM water WHERE water.water_id = #{waterId})) WHERE user_id = (SELECT user_id FROM water WHERE water.water_id = #{waterId})")
    int updateTotalWater(String waterId) ;

}

