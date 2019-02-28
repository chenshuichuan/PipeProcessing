package com.ruoyi.project.system.scanner.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.scanner.mapper.ScannerMapper;
import com.ruoyi.project.system.scanner.domain.Scanner;
import com.ruoyi.project.system.scanner.service.IScannerService;
import com.ruoyi.common.support.Convert;

/**
 * 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否 服务层实现
 * 
 * @author yc
 * @date 2019-02-28
 */
@Service
public class ScannerServiceImpl implements IScannerService 
{
	@Autowired
	private ScannerMapper scannerMapper;

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
    @Override
	public Scanner selectScannerById(Integer id)
	{
	    return scannerMapper.selectScannerById(id);
	}
	
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
	@Override
	public List<Scanner> selectScannerList(Scanner scanner)
	{
	    return scannerMapper.selectScannerList(scanner);
	}
	
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
	@Override
	public int insertScanner(Scanner scanner)
	{
	    return scannerMapper.insertScanner(scanner);
	}
	
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
	@Override
	public int updateScanner(Scanner scanner)
	{
	    return scannerMapper.updateScanner(scanner);
	}

	/**
     * 删除扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteScannerByIds(String ids)
	{
		return scannerMapper.deleteScannerByIds(Convert.toStrArray(ids));
	}
	
}
