package com.shop.mapper;

import com.shop.po.Shopcartitem;
import com.shop.po.ShopcartitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopcartitemMapper {
    int countByExample(ShopcartitemExample example);

    int deleteByExample(ShopcartitemExample example);

    int deleteByPrimaryKey(Integer cartitemid);

    int insert(Shopcartitem record);

    int insertSelective(Shopcartitem record);

    List<Shopcartitem> selectByExample(ShopcartitemExample example);

    Shopcartitem selectByPrimaryKey(Integer cartitemid);

    int updateByExampleSelective(@Param("record") Shopcartitem record, @Param("example") ShopcartitemExample example);

    int updateByExample(@Param("record") Shopcartitem record, @Param("example") ShopcartitemExample example);

    int updateByPrimaryKeySelective(Shopcartitem record);

    int updateByPrimaryKey(Shopcartitem record);
}