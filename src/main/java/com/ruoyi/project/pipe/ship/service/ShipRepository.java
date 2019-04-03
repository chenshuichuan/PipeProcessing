package com.ruoyi.project.pipe.ship.service;


import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import com.ruoyi.project.pipe.ship.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ricardo
 * Created by: ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface ShipRepository extends JpaRepository<Ship,Integer> {
    Ship findByShipCode(String shipCode);
    Ship findByShipName(String shipName);
}
