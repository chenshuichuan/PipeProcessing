package com.ruoyi.project.process.batchArrange.service;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.CutSections;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.pipe.batch.service.BatchRepository;
import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.mapper.CutPlanMapper;
import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.IProcessPlanService;
import com.ruoyi.project.pipe.ship.domain.Ship;
import com.ruoyi.project.pipe.ship.service.ShipRepository;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.service.IUnitService;
import com.ruoyi.project.pipe.unit.service.UnitRepository;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.arrangeTable.service.ArrangeTableRepository;
import com.ruoyi.project.process.arrangeTable.service.IArrangeTableService;
import com.ruoyi.project.process.batchArrange.domain.ArrangeInfo;
import com.ruoyi.project.process.batchProcessing.domain.BatchProcessing;
import com.ruoyi.project.process.batchProcessing.service.IBatchProcessingService;
import com.ruoyi.project.process.middleStatus.service.MiddleStatusRepository;
import com.ruoyi.project.process.order.service.IOrderService;
import com.ruoyi.project.process.pipeProcessing.service.PipeProcessingRepository;
import com.ruoyi.project.process.unitArrange.service.IUnitArrangeService;
import com.ruoyi.project.process.unitProcessing.service.UnitProcessingRepository;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.service.IDictDataService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import com.ruoyi.project.system.workplace.domain.Workplace;
import com.ruoyi.project.system.workplace.service.IWorkplaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.batchArrange.service.IBatchArrangeService;
import com.ruoyi.common.support.Convert;

/**
 * 批次派工 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-09
 */
@Service
public class BatchArrangeServiceImpl implements IBatchArrangeService 
{
    private static final Logger logger=LoggerFactory.getLogger(BatchArrangeServiceImpl.class);
    @Autowired
    private ICutPlanService cutPlanService;
    @Autowired
    private IUnitService unitService;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private IDictDataService dictDataService;
    @Autowired
    private IProcessPlanService processPlanService;
    @Autowired
    private IWorkplaceService workplaceService;
    @Autowired
    private IArrangeTableService arrangeTableService;
    @Autowired
    private ArrangeTableRepository arrangeTableRepository;
    @Autowired
    private MiddleStatusRepository middleStatusRepository;
    @Autowired
    private IUnitArrangeService unitArrangeService;
    @Autowired
    private IBatchProcessingService batchProcessingService;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private UnitProcessingRepository unitProcessingRepository;
    @Autowired
    private PipeProcessingRepository pipeProcessingRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IOrderService orderService;
    @Override
    public int arrangeCutPlan(ArrangeInfo arrangeInfo, CutPlan cutPlan){
        if (null == arrangeInfo){
            logger.error("arrangeInfo is null!");
            return 0;
        }

        //得到当前用户
        User user = ShiroUtils.getSysUser();
        //User user = userMapper.selectUserById(Integer.toUnsignedLong(1));

        String arrangeName = cutPlan.getId()+"-"+cutPlan.getShipName()+"-"+cutPlan.getBatchName();
        //类型为批次派工
        ArrangeTable arrangeTable = new ArrangeTable(1,arrangeName,cutPlan.getId(),
                "下料",user.getUserId().intValue(),0,new Date());

        //得到 三个工单 //生成派工单
        ArrangeTable onebigCutArrange =genArrangeTable(cutPlan.getOnebigCutNumber(),arrangeInfo.getOnebigCutId(),CutSections.onebigCutSection,arrangeTable);
        ArrangeTable oneCutArrange = genArrangeTable(cutPlan.getOneTotalNumber(),arrangeInfo.getOneCutId(),CutSections.oneCutSection,arrangeTable);
        ArrangeTable twoCutArrange = genArrangeTable(cutPlan.getTwoTotalNumber(),arrangeInfo.getTwoCutId(),CutSections.twoCutSection,arrangeTable);


        //得到相关的unit
        List<Unit> unitList = unitRepository.findByPlanId(cutPlan.getId());
        //将下料派工分发到unit层面
        for (Unit unit: unitList){
            //下料派工看下料计划，大岗下料、一部下料、二部下料
            //钢管 外径$140以上 在大岗下料，$114 以下在一部，特殊管在 二部（$22~60: 小管，$76~168:中管，$219+:大管）所以中管有的会在一部下料，有的在大岗下料
            String[] strings = null;
            if(! unit.getCutSection().isEmpty()){
                strings = unit.getCutSection().split(",");
                //单一工段派工
                if(strings.length == 1){
                    logger.info("unit="+unit.getId()+","+unit.getName()+"-"+strings[0]);
                    if(strings[0].equals(CutSections.onebigCutSection)){
                        unitArrangeService.arrangeUnitCut(unit,onebigCutArrange);
                    }
                    else if(strings[0].equals(CutSections.oneCutSection)){
                        unitArrangeService.arrangeUnitCut(unit,oneCutArrange);
                    }
                    else if(strings[0].equals(CutSections.twoCutSection)){
                        unitArrangeService.arrangeUnitCut(unit,twoCutArrange);
                    }else {
                        logger.error("单元下料工段和CutSection数据不匹配，请检查！");
                    }
                }
                //多工段派工
                else {
                    logger.info("unit="+unit.getId()+","+unit.getName()+"-"+strings);
                    unitArrangeService.arrangeUnitCut(unit,onebigCutArrange, oneCutArrange,twoCutArrange);
                }
            }else{
                logger.error("单元数据未解析下料工段！请检查数据！");
            }
        }

        //生成batchProcessing
        if(onebigCutArrange!=null){
            countBatchProcessing(onebigCutArrange,cutPlan);
        }
        if(oneCutArrange!=null){
            countBatchProcessing(oneCutArrange,cutPlan);
        }
        if(twoCutArrange!=null){
            countBatchProcessing(twoCutArrange,cutPlan);
        }
        return 0;
    }
    private ArrangeTable genArrangeTable(Integer cutNumber, Integer workPlaceId, String cutSection, ArrangeTable arrangeTable){
        ArrangeTable arrangeTable1= null;
        if(cutNumber>0){
            if(workPlaceId!=null){
                Workplace workplace =workplaceService.selectWorkplaceById(workPlaceId);

                arrangeTable.setSection(cutSection);
                arrangeTable.setWorkplace(workplace.getWorkplaceCode());
                arrangeTable1 = arrangeTableRepository.save(arrangeTable);
                //更新工位状态
                workplace.setStatus(orderService.getWorkPlaceStatus("下料").getDictValue());
                workplaceService.updateWorkplace(workplace);
            }
            else{
                logger.error("数据不一致！请检查");
            }
        }
        return arrangeTable1;
    }

    private int countBatchProcessing(ArrangeTable arrangeTable,CutPlan cutPlan){
        if(arrangeTable ==null ||cutPlan==null){
            return 0;
        }
        Ship ship = shipRepository.findByShipName(cutPlan.getShipName());
        Batch batch = batchRepository.findByShipCodeAndName(ship.getShipCode(),cutPlan.getBatchName());

        int unitNumber = unitProcessingRepository.countByBatchIdAndArrangeId(batch.getId(),arrangeTable.getId());
        int pipeNumber = pipeProcessingRepository.countByBatchIdAndArrangeId(batch.getId(),arrangeTable.getId());
        //生成batch_processing
        BatchProcessing batchProcessing = new BatchProcessing(batch.getId(),pipeNumber,unitNumber,unitNumber,0,pipeNumber,
                0,arrangeTable.getId(),new Date(),0);

        return batchProcessingService.insertBatchProcessing(batchProcessing);
    }
}
