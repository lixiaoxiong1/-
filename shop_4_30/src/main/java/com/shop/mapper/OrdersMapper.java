package com.shop.mapper;

import com.shop.po.Orders;
import com.shop.po.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    int countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(String oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    ///////////////////////////////////////////////
    int countOrdersByUid(Integer uid);

    List<Orders> findOrderByUidAndPage(@Param("uid") Integer uid, @Param("page") int page, @Param("limitPage")int limitPage);

    //全部订单记录数
    int countAllOrders();

    List<Orders> findAllOrderByPage(@Param("page") int page, @Param("limitPage") int limitPage);

    //根据订单状态，查询符合条件的记录条数
    int countOrdersByState(Integer state);

    List<Orders> findAllOrderByStateAndPage(@Param("state") int state,@Param("page") int page, @Param("limitPage") int limitPage);
}