package com.ruoyi.project.pipe.pipManage.service;


import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import com.ruoyi.project.pipe.pipManage.domain.PipManage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ricardo
 * Created by: ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface PipManageRepository extends JpaRepository<PipManage,Integer> {


}
