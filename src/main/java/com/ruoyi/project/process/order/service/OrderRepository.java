package com.ruoyi.project.process.order.service;


import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.process.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findByUnitHasFAndPipeShapeHasBendAndHasSurfaceTreat(Integer hasF, Integer hasBend, Integer hasTreat);
}
