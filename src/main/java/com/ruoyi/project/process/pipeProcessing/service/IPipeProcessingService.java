package com.ruoyi.project.process.pipeProcessing.service;

import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.order.domain.ProcessStage;
import com.ruoyi.project.process.pipeProcessing.domain.PipeProcessing;

import java.util.List;

/**
 * 管件加工 服务层
 *
 * @author ricardo
 * @date 2019-03-08
 */
public interface IPipeProcessingService {
    /**
     * 查询管件加工信息
     *
     * @param id 管件加工ID
     * @return 管件加工信息
     */
    public PipeProcessing selectPipeProcessingById(Integer id);

    /**
     * 查询管件加工列表
     *
     * @param pipeProcessing 管件加工信息
     * @return 管件加工集合
     */
    public List<PipeProcessing> selectPipeProcessingList(PipeProcessing pipeProcessing);

    /**
     * 新增管件加工
     *
     * @param pipeProcessing 管件加工信息
     * @return 结果
     */
    public int insertPipeProcessing(PipeProcessing pipeProcessing);

    /**
     * 修改管件加工
     *
     * @param pipeProcessing 管件加工信息
     * @return 结果
     */
    public int updatePipeProcessing(PipeProcessing pipeProcessing);

    /**
     * 删除管件加工信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePipeProcessingByIds(String ids);
    /**
     * 派工推送到管件一级
     *
     * @param pipe 需要加工的管件
     * @param arrangeTable 派工单
     * @param unit 管件所属的单元 包含当前工序
     * @param nextStage 下一工序
     * @return 结果
     */
    public int arrangePipe(Pipe pipe , ArrangeTable arrangeTable, Unit unit, ProcessStage nextStage);
}
