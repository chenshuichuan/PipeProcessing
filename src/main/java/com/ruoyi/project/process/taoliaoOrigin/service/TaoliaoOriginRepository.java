package com.ruoyi.project.process.taoliaoOrigin.service;


import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliaoOrigin.domain.TaoliaoOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author  ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface TaoliaoOriginRepository extends JpaRepository<TaoliaoOrigin,Integer> {

    @Transactional
    int deleteByTaoliaoIdAndAlgorithm(Integer taoliaoId, String algorithm);
}
