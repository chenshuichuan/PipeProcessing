package com.ruoyi.project.pipe.processPlan.service;

import java.util.List;

import com.ruoyi.common.constant.CutSections;
import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.mapper.CutPlanMapper;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.service.UnitRepository;
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.mapper.MiddleStatusMapper;
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

    private static final Logger logger = LoggerFactory.getLogger(ProcessPlanServiceImpl.class);

    @Autowired
    private ProcessPlanMapper processPlanMapper;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private MiddleStatusMapper middleStatusMapper;
    @Autowired
    private CutPlanMapper cutPlanMapper;
    @Autowired
    private ProcessPlanRepository processPlanRepository;

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
        if (processPlan == null){
            return 0;
        }
        int returnFlag = 0;
        if(processPlan.getBatchDescription().contains("大管")){
            returnFlag = setUnitesPlanId("L%",processPlan);
        }
        else if(processPlan.getBatchDescription().contains("中小管")){
            returnFlag = setUnitesPlanId("M%",processPlan);
            returnFlag = setUnitesPlanId("S%",processPlan);
        }
        else if(processPlan.getBatchDescription().contains("中管")){
            returnFlag = setUnitesPlanId("M%",processPlan);
        }
        else if(processPlan.getBatchDescription().contains("小管")){
            returnFlag = setUnitesPlanId("S%",processPlan);
        }

        else if(processPlan.getBatchDescription().contains("特殊管")){
            returnFlag = setUnitesPlanId("E%",processPlan);
        }
//        else{
//            setUnitesPlanId("%",processPlan);
//        }
        else{//描述中不包含关键字的话，是报错还是默认包含剩下的所有单元？？
            String message = processPlan.getShipName()+"---"+processPlan.getBatchName()+": 该计划没有描述！无法判断下属单元情况！请规范数据！";
            logger.error(message);
            MiddleStatus middleStatus = new MiddleStatus(message,"pipe_process_plan",processPlan.toString(),"judgeBatchUnitOfPlan(ProcessPlan processPlan)");
            middleStatusMapper.insertMiddleStatus(middleStatus);
            returnFlag =  0;
        }
        //更新plan的解析状态
        if(returnFlag==0){
            processPlan.setAnalysisStatus(-1);
        }
        else{
            processPlan.setAnalysisStatus(1);
        }
        processPlanRepository.save(processPlan);
        return returnFlag;
    }

    /**
     * 根据加工计划和特定的单元名称模式，设定该模式包含的单元对应的下料工段和加工工段
     *@param: batchName :
     **/
    private int setUnitesPlanId(String pattern,ProcessPlan planTable){
        logger.debug("pattern="+pattern);
        logger.debug(planTable.toString());
        String sections = getCutSectionsOfPlan(planTable);
        logger.debug("sections="+sections);
        if(null == sections){
            return 0;
        }
        List<Unit> unitTableList1 = unitRepository.findByBatchNameAndNameLike(planTable.getBatchName(),pattern);
        for (Unit unitTable: unitTableList1){
            //设置单元对应的计划
            unitTable.setPlanId(planTable.getId());
            //设置单元对应的加工工段
            unitTable.setProcessSection(planTable.getProcessPlace());
            //设置单元的下料工段
            unitTable.setCutSection(sections);
            //保存单元信息
            unitRepository.save(unitTable);
            logger.info("1批次"+planTable.getBatchName()+",单元"+unitTable.getName()+",planid="+planTable.getId()
                    +","+planTable.getProcessPlace());

        }
        return 1;
    }
    /**
     * 根据加工计划和返回计划包含的下料工段，","符号分隔
     *@param: batchName :
     **/
    private String getCutSectionsOfPlan(ProcessPlan processPlan){
        CutPlan cutPlan = cutPlanMapper.selectCutPlanById(processPlan.getId());
        if(null == cutPlan){
            String message = processPlan.getId()+"---"+processPlan.getBatchName()+": 该加工计划没有找到对应的下料计划！请检查数据完整性！";
            logger.error(message);
            MiddleStatus middleStatus = new MiddleStatus(message,"pipe_process_plan",processPlan.toString(),"getCutSectionsOfPlan(ProcessPlan processPlan)");
            middleStatusMapper.insertMiddleStatus(middleStatus);
            return null;
        }
        String sections = null;
        String comma = ",";
        //前面是否有
        boolean preHasFlag = false;
        //一部大管下料工段
        if(cutPlan.getOnebigCutNumber()>0){
            sections=CutSections.onebigCutSection;
            preHasFlag = true;
        }
        //一部下料工段
        if(cutPlan.getOneTotalNumber()>0){
            if(preHasFlag){
                sections+=comma+CutSections.oneCutSection;
            }
            else{
                sections = CutSections.oneCutSection;
            }
        }
        //二部下料工段
        if(cutPlan.getTwoTotalNumber()>0){
            if(preHasFlag){
                sections+=comma+CutSections.twoCutSection;
            }
            else {
                sections = CutSections.twoCutSection;
            }
        }
        return  sections;
    }

    /**
     * 判断某加工计划下包含的单元、管件
     * @param planName 要分析判断的计划名称
     * @return int 分析成功返回1，否则返回0
     * */
    @Override
    public int judgeBatchUnitByPlanName(String  planName){
        if(planName.isEmpty()){
            return 0;
        }
        List<ProcessPlan> processPlanList = processPlanRepository.findByPlanName(planName);
        if(null == processPlanList||processPlanList.size()<1){
            String message = planName+": 该加工计划名称没有找到对应的加工计划！请检查数据完整性！";
            logger.error(message);
            MiddleStatus middleStatus = new MiddleStatus(message,"pipe_process_plan","查找"+planName,"judgeBatchUnitByPlanName(String  planName)");
            middleStatusMapper.insertMiddleStatus(middleStatus);
            return 0;
        }
        for (ProcessPlan processPlan: processPlanList){
            judgeBatchUnitOfPlan(processPlan);
        }
        return  1;
    }
}
