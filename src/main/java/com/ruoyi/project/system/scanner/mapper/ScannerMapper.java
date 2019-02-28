package com.ruoyi.project.system.scanner.mapper;

import com.ruoyi.project.system.scanner.domain.Scanner;
import java.util.List;	

/**
 * 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否 数据层
 * 
 * @author yc
 * @date 2019-02-28
 */
public interface ScannerMapper 
{
	/**
     * 查询扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否信息
     * 
     * @param id 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否ID
     * @return 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否信息
     */
	public Scanner selectScannerById(Integer id);
	
	/**
     * 查询扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否列表
     * 
     * @param scanner 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否信息
     * @return 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否集合
     */
	public List<Scanner> selectScannerList(Scanner scanner);
	
	/**
     * 新增扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否
     * 
     * @param scanner 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否信息
     * @return 结果
     */
	public int insertScanner(Scanner scanner);
	
	/**
     * 修改扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否
     * 
     * @param scanner 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否信息
     * @return 结果
     */
	public int updateScanner(Scanner scanner);
	
	/**
     * 删除扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否
     * 
     * @param id 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否ID
     * @return 结果
     */
	public int deleteScannerById(Integer id);
	
	/**
     * 批量删除扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteScannerByIds(String[] ids);
	
}