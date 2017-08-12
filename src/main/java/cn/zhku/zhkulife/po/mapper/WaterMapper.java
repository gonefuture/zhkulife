package cn.zhku.zhkulife.po.mapper;

import cn.zhku.zhkulife.po.entity.Water;
import cn.zhku.zhkulife.po.entity.WaterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterMapper {
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