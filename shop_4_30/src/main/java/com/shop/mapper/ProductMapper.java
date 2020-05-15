package com.shop.mapper;

import com.shop.po.Product;
import com.shop.po.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
//-------------------------------------------------------------------------
    int countProducyByCid(int cid);

    List<Product> findProductByCid(@Param("cid") int cid,@Param("beginPage") int beginPage,@Param("end") int end);

    int countProducyByCsid(int csid);

    List<Product> findProductBycsid(@Param("csid") int csid,@Param("page") int page, @Param("limitPage")int limitPage);

    List<Product> findAllProduct(@Param("page")int page,@Param("limitPage") int limitPage);

    List<Product> searchProduct(String condition);
}