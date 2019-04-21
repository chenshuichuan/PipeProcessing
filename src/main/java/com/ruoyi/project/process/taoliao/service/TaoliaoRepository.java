package com.ruoyi.project.process.taoliao.service;


import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author  ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface TaoliaoRepository extends JpaRepository<Taoliao,Integer> {
    /**
     * 删除派工单下所有的套料管材记录
     * @param arrangeId 派工单id
     * @return int 删除的管件数量
     * */
    @Transactional
    int deleteByArrangeId(Integer arrangeId);

    List<Taoliao> findByArrangeIdAndPlanId(Integer arrangeId, Integer planId);

    /**
     * 查找派工下的唯一下料管材记录
     * */
    Taoliao findByArrangeIdAndPlanIdAndPipeMaterial(Integer arrangeId, Integer planId, String pipeMaterial);
}
