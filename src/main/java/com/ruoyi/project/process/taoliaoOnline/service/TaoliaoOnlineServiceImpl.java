package com.ruoyi.project.process.taoliaoOnline.service;

import java.util.*;

import com.ruoyi.common.constant.AlgorithmConstants;
import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.pipe.batch.service.BatchRepository;
import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import com.ruoyi.project.pipe.pipCutting.mapper.PipCuttingMapper;
import com.ruoyi.project.pipe.pipPipe.domain.PipPipe;
import com.ruoyi.project.pipe.pipPipe.service.IPipPipeService;
import com.ruoyi.project.pipe.pipPipe.service.PipPipeRepository;
import com.ruoyi.project.pipe.pipe.service.PipeRepository;
import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import com.ruoyi.project.pipe.ship.domain.Ship;
import com.ruoyi.project.pipe.ship.service.ShipRepository;
import com.ruoyi.project.pipe.unit.service.IUnitService;
import com.ruoyi.project.pipe.workPipe.domain.WorkPipe;
import com.ruoyi.project.pipe.workPipe.service.WorkPipeRepository;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.arrangeTable.service.ArrangeTableRepository;
import com.ruoyi.project.process.batchArrange.service.IBatchArrangeService;
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.service.MiddleStatusRepository;
import com.ruoyi.project.process.pipeProcessing.mapper.PipeProcessingMapper;
import com.ruoyi.project.process.pipeProcessing.service.IPipeProcessingService;
import com.ruoyi.project.process.pipeProcessing.service.PipeProcessingRepository;
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliao.service.ITaoliaoService;
import com.ruoyi.project.process.taoliao.service.TaoliaoRepository;
import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;
import com.ruoyi.project.process.taoliaoResult.service.TaoliaoResultRepository;
import com.ruoyi.project.system.workplace.mapper.WorkplaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.taoliaoOnline.service.ITaoliaoOnlineService;
import com.ruoyi.common.support.Convert;

/**
 * 在线套料 服务层实现
 * 
 * @author ricardo
 * @date 2019-04-19
 */
@Service
public class TaoliaoOnlineServiceImpl implements ITaoliaoOnlineService {
    @Autowired
    private TaoliaoRepository taoliaoRepository;
    @Autowired
    private TaoliaoResultRepository taoliaoResultRepository;
    @Autowired
    private MiddleStatusRepository middleStatusRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private PipPipeRepository pipPipeRepository;
    @Autowired
    private IPipPipeService pipPipeService;
    @Autowired
    private WorkPipeRepository workPipeRepository;
    @Autowired
    private ITaoliaoService taoliaoService;
    @Autowired
    private ProcessPlanRepository processPlanRepository;
    @Autowired
    private IUnitService unitService;
    @Autowired
    private IBatchArrangeService batchArrangeService;
    @Autowired
    private ICutPlanService cutPlanService;
    @Autowired
    private PipeRepository pipeRepository;
    @Autowired
    private ArrangeTableRepository arrangeTableRepository;
    @Autowired
    private WorkplaceMapper workplaceMapper;
    @Autowired
    private IPipeProcessingService pipeProcessingService;
    @Autowired
    private PipeProcessingRepository pipeProcessingRepository;
    @Autowired
    private PipeProcessingMapper pipeProcessingMapper;
    @Autowired
    private PipCuttingMapper pipCuttingMapper;


    @Override
    public int generateTaoliao(ArrangeTable arrangeTable) {
        if(arrangeTable ==null){
            return 0;
        }
        //先删除该计划的套料表信息
        taoliaoService.deleteTaoliaoByArrangeTable(arrangeTable);
        //得到派工单下所有管子
        List<Integer> pipeIds = pipeProcessingMapper.selectByArrangeId(arrangeTable.getId());
        List<PipCutting> pipCuttingList = pipCuttingMapper.selectPipCuttingByIds(pipeIds);

        //对管件按照管材字段排好序
        Collections.sort(pipCuttingList,new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                PipCutting u1 = (PipCutting)o1;
                PipCutting u2 = (PipCutting)o2;
                int i = u1.getPipeSpecification().compareTo(u2.getPipeSpecification());
                return i;
            }
        });
        System.out.println("generateTaoliao pipeSize="+pipCuttingList.size());
        generateTaoliao(arrangeTable,pipCuttingList);
        return 0;
    }

    /**
     * 根据已经排好序的下料管子，生成下料管材
     * */
    private void generateTaoliao(ArrangeTable arrangeTable, List<PipCutting> pipCuttingList){

        if(arrangeTable==null||pipCuttingList==null){
            return;
        }
        CutPlan cutPlan = cutPlanService.selectCutPlanById(arrangeTable.getPlanId());
        Ship ship = shipRepository.findByShipName(cutPlan.getShipName());
        Batch batch = batchRepository.findByShipCodeAndName(ship.getShipCode(),cutPlan.getBatchName());

        int totalLength=0;
        int pipeNumber =0;
        Taoliao taoliao=null;

        for (int i=0;i<pipCuttingList.size();i++){

            PipCutting pipeTable = pipCuttingList.get(i);
            //第一个，先生成套料管材信息
            if(0 == pipeNumber && 0==totalLength){
                taoliao = new Taoliao(arrangeTable.getPlanId(),batch.getId(),
                        batch.getName(),arrangeTable.getId(),pipeTable.getPipeSpecification(),
                        0,new Date());
                taoliao = taoliaoRepository.save(taoliao);
            }

            //提前生成套料结果基本信息taoliao_result
            generateTaoliaoResutl(ship,batch,pipCuttingList.get(i), taoliao);

            //下一个和当前相同
            if(i<pipCuttingList.size()-1&&
                    pipeTable.getPipeSpecification().equals(pipCuttingList.get(i+1).getPipeSpecification())){
                totalLength += pipeTable.getCutLength();
                pipeNumber++;
            }
            else{
                //下一个和当前不同，立即保存当前
                taoliao.setTotalLength(totalLength+pipeTable.getCutLength());
                taoliao.setPipeNumber(pipeNumber+1);
                //保存到数据库
                taoliaoRepository.save(taoliao);
                taoliao = null;
                totalLength=0;
                pipeNumber=0;
            }
        }
    }
    /**
     * 生成下料result的基本信息
     * */
    private void generateTaoliaoResutl(Ship ship,Batch batch, PipCutting pipCutting, Taoliao taoliao){
        if(null==batch||null ==pipCutting||null == taoliao){
            return;
        }
        PipPipe pipPipe = pipPipeService.selectPipPipeById(pipCutting.getAssemblyPipeId());
        List<WorkPipe> workPipeList = workPipeRepository.findByAssemblyPipeIdAndShapeShipIdAndCutLength(
                pipCutting.getAssemblyPipeId(), Integer.parseInt(ship.getShapeShipId()),pipCutting.getCutLength());

        TaoliaoResult taoliaoResult = new TaoliaoResult();
        taoliaoResult.setTaoliaoId(taoliao.getId());
        taoliaoResult.setPipeId(pipCutting.getOldId());
        taoliaoResult.setBatchId(batch.getId());
        taoliaoResult.setBatchName(batch.getName());

        taoliaoResult.setCollecteCode(pipPipe.getSetCode());
        if(workPipeList!=null&&workPipeList.size()>=1){
            taoliaoResult.setSurfaceTreat(workPipeList.get(0).getSurfaceTreat());
        }
        taoliaoResult.setPipeShape(pipCutting.getPipeShape());
        taoliaoResult.setNoInstall(pipCutting.getNoInstalled());
        taoliaoResult.setCutLength(pipCutting.getCutLength());
        taoliaoResult.setPipeMaterial(pipCutting.getPipeSpecification());

        taoliaoResult.setOriginId(0);
        taoliaoResult.setOriginIndex(0);
        taoliaoResult.setOriginLength(0);

        taoliaoResult.setAlgorithm(AlgorithmConstants.DynamicProgramming);
        taoliaoResultRepository.save(taoliaoResult);
        //save 之后，generateTaoliao会获得数据库返回的一份复制。。。可怕
        taoliaoResult.setId(null);
        taoliaoResult.setAlgorithm(AlgorithmConstants.GeneticAlgorithm);
        taoliaoResultRepository.save(taoliaoResult);
    }


}
