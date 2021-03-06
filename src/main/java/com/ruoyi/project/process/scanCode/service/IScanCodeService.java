package com.ruoyi.project.process.scanCode.service;

import com.ruoyi.project.process.scanCode.domain.ScanCode;
import java.util.List;

/**
 * 扫码记录 服务层
 * 
 * @author ricardo
 * @date 2019-11-16
 */
public interface IScanCodeService 
{
	/**
     * 查询扫码记录信息
     * 
     * @param id 扫码记录ID
     * @return 扫码记录信息
     */
	public ScanCode selectScanCodeById(Integer id);
	
	/**
     * 查询扫码记录列表
     * 
     * @param scanCode 扫码记录信息
     * @return 扫码记录集合
     */
	public List<ScanCode> selectScanCodeList(ScanCode scanCode);
	
	/**
     * 新增扫码记录
     * 
     * @param scanCode 扫码记录信息
     * @return 结果
     */
	public int insertScanCode(ScanCode scanCode);
	/**
	 * 新增扫码记录
	 *
	 * @param content 扫码记录信息
	 * @return 结果
	 */
	public int insertScanCode(String content);
	/**
     * 修改扫码记录
     * 
     * @param scanCode 扫码记录信息
     * @return 结果
     */
	public int updateScanCode(ScanCode scanCode);
		
	/**
     * 删除扫码记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteScanCodeByIds(String ids);
	
}
