package com.ruoyi.project.pipe.processPlan.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.processPlan.mapper.ProcessPlanMapper;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.IProcessPlanService;
import com.ruoyi.common.support.Convert;

/**
 * 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan 服务层实现
 *
 * @author ricardo
 * @date 2019-03-02
 */
@Service
public class ProcessPlanServiceImpl implements IProcessPlanService {

    private static final Logger loger = LoggerFactory.getLogger(ProcessPlanServiceImpl.class);

    @Autowired
    private ProcessPlanMapper processPlanMapper;

    /**
     * 查询下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     *
     * @param id 下料计划，不包含后续加工，后续加工计划请查看pipe_process_planID
     * @return 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     */
    @Override
    public ProcessPlan selectProcessPlanById(Integer id) {
        return processPlanMapper.selectProcessPlanById(id);
    }

    /**
     * 查询下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan列表
     *
     * @param processPlan 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     * @return 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan集合
     */
    @Override
    public List<ProcessPlan> selectProcessPlanList(ProcessPlan processPlan) {
        return processPlanMapper.selectProcessPlanList(processPlan);
    }

    /**
     * 新增下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan
     *
     * @param processPlan 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     * @return 结果
     */
    @Override
    public int insertProcessPlan(ProcessPlan processPlan) {
        return processPlanMapper.insertProcessPlan(processPlan);
    }

    /**
     * 修改下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan
     *
     * @param processPlan 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     * @return 结果
     */
    @Override
    public int updateProcessPlan(ProcessPlan processPlan) {
        return processPlanMapper.updateProcessPlan(processPlan);
    }

    /**
     * 删除下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProcessPlanByIds(String ids) {
        return processPlanMapper.deleteProcessPlanByIds(Convert.toStrArray(ids));
    }

    /**
     * 判断某加工计划下包含的单元、管件
     * @param processPlan 要分析判断的计划
     * @return int 分析成功返回1，否则返回0
     * */
    @Override
    public int judgeBatchUnitOfPlan(ProcessPlan processPlan){
//        if(processPlan.getBatchDescription().contains("大管")){
//            setUnitesPlanId(batchName,"L%",planTable);
//        }
//        else if(processPlan.getBatchDescription().contains("中小管")){
//            setUnitesPlanId(batchName,"M%",planTable);
//            setUnitesPlanId(batchName,"S%",planTable);
//        }
//        else if(processPlan.getBatchDescription().contains("中管")){
//            setUnitesPlanId(batchName,"M%",planTable);
//        }
//        else if(processPlan.getBatchDescription().contains("小管")){
//            setUnitesPlanId(batchName,"S%",planTable);
//        }
//
//        else if(processPlan.getBatchDescription().contains("特殊管")){
//            setUnitesPlanId(batchName,"E%",planTable);
//        }
//        else{
//            String message = processPlan.getShipName()+"---"+processPlan.getBatchName()+": 该计划没有描述！无法判断下属单元情况！请规范数据！";
//            loger.error(message);
//
//        }
        return 1;
    }

}
