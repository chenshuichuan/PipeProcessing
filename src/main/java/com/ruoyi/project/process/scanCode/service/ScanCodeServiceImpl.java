package com.ruoyi.project.process.scanCode.service;

import java.util.List;

import com.ruoyi.project.system.files.controller.FilesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.scanCode.mapper.ScanCodeMapper;
import com.ruoyi.project.process.scanCode.domain.ScanCode;
import com.ruoyi.project.process.scanCode.service.IScanCodeService;
import com.ruoyi.common.support.Convert;

/**
 * 扫码记录 服务层实现
 *
 * @author ricardo
 * @date 2019-11-16
 */
@Service
public class ScanCodeServiceImpl implements IScanCodeService {
  private static final Logger logger = LoggerFactory.getLogger(ScanCodeServiceImpl.class);

  @Autowired
  private ScanCodeMapper scanCodeMapper;
  /**
   * 查询扫码记录信息
   *
   * @param id 扫码记录ID
   * @return 扫码记录信息
   */
  @Override
  public ScanCode selectScanCodeById(Integer id) {
    return scanCodeMapper.selectScanCodeById(id);
  }

  /**
   * 查询扫码记录列表
   *
   * @param scanCode 扫码记录信息
   * @return 扫码记录集合
   */
  @Override
  public List<ScanCode> selectScanCodeList(ScanCode scanCode) {
    return scanCodeMapper.selectScanCodeList(scanCode);
  }

  /**
   * 新增扫码记录
   *
   * @param scanCode 扫码记录信息
   * @return 结果
   */
  @Override
  public int insertScanCode(ScanCode scanCode) {
    return scanCodeMapper.insertScanCode(scanCode);
  }

  @Override
  public int insertScanCode(String content) {
    logger.debug("scan content="+content);
    return 0;
  }

  /**
   * 修改扫码记录
   *
   * @param scanCode 扫码记录信息
   * @return 结果
   */
  @Override
  public int updateScanCode(ScanCode scanCode) {
    return scanCodeMapper.updateScanCode(scanCode);
  }

  /**
   * 删除扫码记录对象
   *
   * @param ids 需要删除的数据ID
   * @return 结果
   */
  @Override
  public int deleteScanCodeByIds(String ids) {
    return scanCodeMapper.deleteScanCodeByIds(Convert.toStrArray(ids));
  }

}
