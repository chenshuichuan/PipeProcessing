package com.ruoyi.project.system.files.service;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ReadPlanTable;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.pipe.common.PlanTable;
import com.ruoyi.project.pipe.cutPlan.service.CutPlanRepository;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import com.ruoyi.project.system.files.domain.Files;
import com.ruoyi.project.system.files.mapper.FilesMapper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * 文件上传 服务层实现
 *
 * @author yc
 * @date 2018-12-20
 */
@Service
public class FilesServiceImpl implements IFilesService {
    @Autowired
    private FilesMapper filesMapper;

    @Autowired
    private CutPlanRepository cutPlanRepository;
    @Autowired
    private ProcessPlanRepository processPlanRepository;
    /**
     * 查询文件上传信息
     *
     * @param id 文件上传ID
     * @return 文件上传信息
     */
    @Override
    public Files selectFilesById(Integer id) {
        return filesMapper.selectFilesById(id);
    }

    /**
     * 查询文件上传列表
     *
     * @param files 文件上传信息
     * @return 文件上传集合
     */
    @Override
    public List<Files> selectFilesList(Files files) {
        return filesMapper.selectFilesList(files);
    }

    /**
     * 新增文件上传
     *
     * @param files 文件上传信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertFiles(Files files) {
        files.setCreateBy(ShiroUtils.getLoginName());
        files.setCreateByName(ShiroUtils.getSysUser().getUserName());
        return filesMapper.insertFiles(files);
    }

    /**
     * 修改文件上传
     *
     * @param files 文件上传信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateFiles(Files files, Boolean isFile) {
        int rtn = 0;
        //修改新数据
        rtn = filesMapper.updateFiles(files);
        if (rtn > 0) {
            //先判断有没有文件
            Files oldFiles = filesMapper.selectFilesById(files.getId());
            File file = new File(oldFiles.getUrl());
            if (file.isFile()) {
                if (files != null && isFile) {
                    file.delete();
                } else {
                    String url = FileUploadUtils.getDefaultBaseDir() + files.getFileName() + "." + oldFiles.getSuffix();
                    file.renameTo(new File(url ));
                }
            }
        }

        return rtn;
    }

    /**
     * 删除文件上传对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFilesByIds(String ids) {
        int rtn = 0;
        if (ids.length() > 0) {
            String[] id = ids.split(",");
            for (int i = 0; i < id.length; i++) {
                Files files = filesMapper.selectFilesById(Integer.valueOf(id[i]));
                if (files != null) {
                    rtn = filesMapper.deleteFilesByIds(Convert.toStrArray(ids));
                    //删除成功后要移除文件
                    if (rtn > 0) {
                        File file = new File(files.getUrl());
                        if (file.isFile()) {
                            file.delete();
                        }
                    }
                }
            }
        }
        return rtn;
    }

    @Override
    public String checkFileNameUnqiue(Files files) {
        List<Files> filesList = filesMapper.selectFilesList(files);
        if (filesList != null && filesList.size() > 0) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public List<Files> selectFilesListNoSave(Files files) {
        List<Files> list = filesMapper.selectFilesListNoSave(files);

        return list;
    }

    @Override
    public AjaxResult readXlsFile(Files files) {
        try {
            List<PlanTable> planTableList = ReadPlanTable.readExcel(files);
            for (PlanTable planTable: planTableList) {
                ProcessPlan plan = planTable.getProcessPlan();
                /**
                 * 通过“序号+船名+批次+加工点”作为唯一数据判断，进而覆盖更新数据，防止重复解析计划
                 */
                ProcessPlan processPlan = processPlanRepository.findBySerialNumberAndShipNameAndBatchNameAndProcessPlace(
                        plan.getSerialNumber(),plan.getShipName(),plan.getBatchName(),plan.getProcessPlace()
                );
                //如果已经存在，则覆盖原mysql中的数据进行更新
                if (processPlan!=null){
                    plan.setId(processPlan.getId());
                }

                //cutPlan的id是引用processPlan的id的 保证他们同属一个计划id
                plan = processPlanRepository.save(plan);
                if(plan!=null&&plan.getId()>0){
                    planTable.getCutPlan().setId(plan.getId());
                    cutPlanRepository.save(planTable.getCutPlan());
                }

            }
            return AjaxResult.success("解析计划成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return AjaxResult.error("解析计划失败！");
    }

}
