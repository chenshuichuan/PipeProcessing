package com.ruoyi.project.process.taoliaoOnline.service;

import java.util.*;

import com.ruoyi.project.algorithm.AlgorithmConstants;
import com.ruoyi.project.algorithm.dynamic.DynamicProgramming;
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
import com.ruoyi.project.process.middleStatus.service.MiddleStatusRepository;
import com.ruoyi.project.process.pipeProcessing.mapper.PipeProcessingMapper;
import com.ruoyi.project.process.pipeProcessing.service.IPipeProcessingService;
import com.ruoyi.project.process.pipeProcessing.service.PipeProcessingRepository;
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliao.service.ITaoliaoService;
import com.ruoyi.project.process.taoliao.service.TaoliaoRepository;
import com.ruoyi.project.process.taoliaoOnline.domain.OriginInfo;
import com.ruoyi.project.process.taoliaoOrigin.domain.TaoliaoOrigin;
import com.ruoyi.project.process.taoliaoOrigin.mapper.TaoliaoOriginMapper;
import com.ruoyi.project.process.taoliaoOrigin.service.TaoliaoOriginRepository;
import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;
import com.ruoyi.project.process.taoliaoResult.mapper.TaoliaoResultMapper;
import com.ruoyi.project.process.taoliaoResult.service.TaoliaoResultRepository;
import com.ruoyi.project.system.workplace.mapper.WorkplaceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 在线套料 服务层实现
 * 
 * @author ricardo
 * @date 2019-04-19
 */
@Service
public class TaoliaoOnlineServiceImpl implements ITaoliaoOnlineService {
    private static final Logger logger = LoggerFactory.getLogger(TaoliaoOnlineServiceImpl.class);

    @Autowired
    private TaoliaoRepository taoliaoRepository;
    @Autowired
    private TaoliaoResultRepository taoliaoResultRepository;
    @Autowired
    private TaoliaoOriginRepository taoliaoOriginRepository;
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
    private TaoliaoOriginMapper taoliaoOriginMapper;
    @Autowired
    private TaoliaoResultMapper taoliaoResultMapper;

    @Autowired
    private ICutPlanService cutPlanService;
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

        taoliaoResult.setAlgorithm(AlgorithmConstants.DynamicProgrammingName);
        taoliaoResultRepository.save(taoliaoResult);
        //save 之后，generateTaoliao会获得数据库返回的一份复制。。。可怕
        taoliaoResult.setId(null);
        taoliaoResult.setAlgorithm(AlgorithmConstants.GeneticAlgorithmName);
        taoliaoResultRepository.save(taoliaoResult);
    }


    /**
     * 根据某个要套料的管材Taoliao去套料
     * **/
    @Override
    public int dynamicProgramming(Taoliao taoliao){
        if(taoliao==null){
            return 0;
        }
        TaoliaoOrigin taoliaoOrigin=new TaoliaoOrigin();
        taoliaoOrigin.setTaoliaoId(taoliao.getId());
        //taoliaoOrigin.setMaterial(taoliao.getPipeMaterial());
        taoliaoOrigin.setAlgorithm(AlgorithmConstants.DynamicProgrammingName);
        List<TaoliaoOrigin> taoliaoOriginList = taoliaoOriginMapper.selectTaoliaoOriginList(taoliaoOrigin);
        //对管件原料按照原料长度排好序，有助于优化算法
        Collections.sort(taoliaoOriginList,new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                TaoliaoOrigin u1 = (TaoliaoOrigin)o1;
                TaoliaoOrigin u2 = (TaoliaoOrigin)o2;
                int i = u1.getOriginLength().compareTo(u2.getOriginLength());
                return i;
            }
        });
        TaoliaoResult taoliaoResult = new TaoliaoResult();
        taoliaoResult.setAlgorithm(AlgorithmConstants.DynamicProgrammingName);
        taoliaoResult.setTaoliaoId(taoliao.getId());
        taoliaoResult.setPipeMaterial(taoliao.getPipeMaterial());
        List<TaoliaoResult> taoliaoResultList = taoliaoResultMapper.selectTaoliaoResultList(taoliaoResult);

        logger.info("find origin size = "+taoliaoOriginList.size());
        logger.info("find results size = "+taoliaoResultList.size());
        //要考虑taoliaoOriginList 和taoliaoResultList 是否被dp更改也能反映回来？
        for (TaoliaoOrigin origin: taoliaoOriginList){

            DynamicProgramming dp = new DynamicProgramming();
            //动态规划套料
            logger.info("results size = "+taoliaoResultList.size());
            dp.init(origin,taoliaoResultList);
            //origin = dp.getTaoliaoOrigin();
            //List<TaoliaoResult> resultList = dp.getTaoliaoGaList();
            taoliaoOriginMapper.updateTaoliaoOrigin(origin);
            logger.info(origin.toString());
            List<TaoliaoResult> resultList =taoliaoResultList;
            // 对套料完成的进行保存和剔除
            taoliaoResultList = new ArrayList<>();
            for (TaoliaoResult result1: resultList){
                logger.info(result1.toString());

                if(result1.getOriginId()!=0){
                    taoliaoResultRepository.save(result1);
                }
                else{
                    taoliaoResultList.add(result1);
                }
            }
        }
        return 1;
    }

    /**
     * 输入管材原料,生成TaoliaoOrigin信息
     * @param taoliao 对应的套料taoliao次
     * @param originInfos 用户输入的管材原料数据信息
     * **/
    @Override
    public int inputOrigin(Taoliao taoliao, List<OriginInfo> originInfos, String algorithm){

        //i用于标志原料管序号
        int i=0;
        for (OriginInfo originInfo: originInfos){
            for (int j=0;j<originInfo.getOriginNumber();j++){
                i++;
                TaoliaoOrigin taoliaoOrigin = new TaoliaoOrigin();
                taoliaoOrigin.setTaoliaoId(taoliao.getId());
                taoliaoOrigin.setMaterial(taoliao.getPipeMaterial());
                taoliaoOrigin.setOriginLength(originInfo.getOriginLength());
                taoliaoOrigin.setRemainLength(originInfo.getOriginLength());
                taoliaoOrigin.setOriginIndex(i);
                taoliaoOrigin.setAlgorithm(algorithm);
                taoliaoOriginMapper.insertTaoliaoOrigin(taoliaoOrigin);
            }
        }
        return 1;
    }
    /**
     * 删除TaoliaoOrigin信息
     * @param taoliao 对应的套料taoliao次
     * @param algorithm 套料算法
     * **/
    @Override
    public int deleteOrigin(Taoliao taoliao, String algorithm){
        taoliaoOriginRepository.deleteByTaoliaoIdAndAlgorithm(taoliao.getId(),algorithm);
        return 1;
    }
}
