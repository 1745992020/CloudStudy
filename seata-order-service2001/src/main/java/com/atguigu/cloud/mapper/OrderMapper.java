package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther zzyy
 * @create 2023-12-01 17:50
 */

@Mapper
public interface OrderMapper {
    int insert(Order order);

    Order selectOne(Long id);

    int updateById(Order orderFromDB);
}
