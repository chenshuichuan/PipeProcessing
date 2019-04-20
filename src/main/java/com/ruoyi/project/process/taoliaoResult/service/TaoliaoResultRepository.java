package com.ruoyi.project.process.taoliaoResult.service;


import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author  ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface TaoliaoResultRepository extends JpaRepository<TaoliaoResult,Integer> {

    List<TaoliaoResult> findByTaoliaoId(Integer taoliaoId);

    @Transactional
    int deleteByTaoliaoId(Integer taoliaoId);
}
