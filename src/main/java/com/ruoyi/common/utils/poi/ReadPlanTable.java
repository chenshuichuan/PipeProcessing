package com.ruoyi.common.utils.poi;/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/3/2
 * Time: 9:02
 */

import com.ruoyi.common.utils.DateUtils2;
import com.ruoyi.project.pipe.common.PlanTable;
import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.system.files.domain.Files;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *@ClassName: ReadPlanTable
 *@Description: TODO
 *@Author: Ricardo
 *@Date: 2019/3/2 9:02
 **/
public class ReadPlanTable {


    /**
     * @param file
     * @throws EncryptedDocumentException
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static List<PlanTable> readExcel(Files file)
            throws EncryptedDocumentException, IOException, InvalidFormatException {
        List<PlanTable> resultList = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        // 同时支持ecxel-2003/2007/2010
        String planTableName = file.getFileName();
        File excelFile = new File(file.getUrl());
        FileInputStream is = new FileInputStream(excelFile);
        // 这种方式 Excel 2003/2007/2010都是可以处理的
        Workbook workbook = WorkbookFactory.create(is);
        FormulaEvaluator formulaEvaluator = null;

        formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
        int sheetCount = workbook.getNumberOfSheets();
        // 可以遍历每个sheet
        //当前仅读取第一个sheet

        Sheet sheet = workbook.getSheetAt(0);
        // 获取总行数
        int rowCount = sheet.getPhysicalNumberOfRows();
        //int planId = planTableService.selectMaxId() + 1;
        for (int r = 3; r < rowCount; r++) {
            Row row = sheet.getRow(r);
            //int cellCount = row.getPhysicalNumberOfCells();
            int cellCount = 31;
            PlanTable planTable = new PlanTable();
            planTable.setPlanName(planTableName);

            for (int c = 0; c < cellCount; c++) {
                //System.out.println("c = "+c);
                Cell cell = row.getCell(c);
                if (cell == null){
                    continue;
                }
                CellType cellTypeEnum = cell.getCellTypeEnum();
                switch (c) {
                    //序号
                    case 0:
                        if (cellTypeEnum.equals(CellType.NUMERIC)){
                            planTable.setSerialNumber(String.valueOf((int)cell.getNumericCellValue()));
                        }
                        else if(cellTypeEnum.equals(CellType.STRING)) planTable.setSerialNumber(cell.getStringCellValue());
                        else continue;
                        break;
                    //船名
                    case 1:
                        if (cellTypeEnum.equals(CellType.STRING))
                            planTable.setShipName(cell.getStringCellValue());
                        break;
                        //批次名
                    case 2:
                        if (cellTypeEnum.equals(CellType.NUMERIC)){
                            planTable.setBatchName(String.valueOf((int)cell.getNumericCellValue()));
                        }
                        else if (cellTypeEnum.equals(CellType.STRING))
                            planTable.setBatchName(cell.getStringCellValue());
                        break;
                        //批次描述
                    case 3:
                        if (cellTypeEnum.equals(CellType.STRING))
                            planTable.setBatchDescription(cell.getStringCellValue());
                        break;
                        //包含分段
                    case 4:
                        if (cellTypeEnum.equals(CellType.STRING))
                            planTable.getProcessPlan().setSections(cell.getStringCellValue());
                        break;
                        //包含托盘
                    case 5:
                        if (cellTypeEnum.equals(CellType.STRING))
                            planTable.getProcessPlan().setStocks(cell.getStringCellValue());
                        break;
                        //加工点
                    case 6:
                        if (cellTypeEnum.equals(CellType.STRING))
                            planTable.getProcessPlan().setProcessPlace(cell.getStringCellValue());
                        break;
                    //加工数量
                    case 7:
                        if (cellTypeEnum.equals(CellType.NUMERIC)){
                            planTable.getProcessPlan().setNumber((int) cell.getNumericCellValue());
                        }
                        else if (cellTypeEnum.equals(CellType.STRING)){
                            int number = Integer.parseInt(cell.getStringCellValue());
                            planTable.getProcessPlan().setNumber(number);
                        }else{
                            planTable.getProcessPlan().setNumber(0);
                        }
                        break;
                        //含光身管
                    case 8:
                        if (cellTypeEnum.equals(CellType.NUMERIC)){
                            planTable.getProcessPlan().setLightBodyPipe(String.valueOf((int)cell.getNumericCellValue()));
                        }
                        else if (cellTypeEnum.equals(CellType.STRING)){
                            String str = cell.getStringCellValue();
                            planTable.getProcessPlan().setLightBodyPipe(str.length()>0?str:"0");
                        }
                        else{
                            planTable.getProcessPlan().setLightBodyPipe("0");
                        }
                        break;
                        //计划开工时间
                    case 11:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getProcessPlan().setPlanStart(cell.getDateCellValue());
                        break;
                        //计划结束时间
                    case 12:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getProcessPlan().setPlanEnd(cell.getDateCellValue());
                        else if (cellTypeEnum.equals(CellType.FORMULA)){
                            //System.out.println("Formula完工时间="+cell.getCellFormula());
                            int dayNumber =  getCellValueFormula(cell.getCellFormula(),"[+]");
                            planTable.setPlanEndDateNumber(dayNumber);
//                            formulaEvaluator.evaluate(cell).getDateCellValue
                        }
                        break;
                        //实际开工时间
                    //计划开工时间
                    case 13:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getProcessPlan().setMiddleDate(cell.getDateCellValue());
                        break;
                    case 14:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getProcessPlan().setActualStart(cell.getDateCellValue());
                        break;
                        //实际结束时间
                    case 15:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getProcessPlan().setActualEnd(cell.getDateCellValue());
                        break;
                        //加工备注
                    case 16:
                        System.out.println(r + "/" + rowCount);
                        if (cellTypeEnum.equals(CellType.STRING))
                            planTable.getProcessPlan().setRemark(cell.getStringCellValue());
                        break;
                        //发图时间
                    case 17:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getProcessPlan().setSendPicTime(cell.getDateCellValue());
                        break;
                        //以下为下料计划部分
                    //完工时间
                    case 19:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getCutPlan().setFinishedDate(cell.getDateCellValue());
                        else if (cellTypeEnum.equals(CellType.FORMULA)){
                            //System.out.println("Formula完工时间="+cell.getCellFormula());
                            int dayNumber =  getCellValueFormula(cell.getCellFormula(),"-");
                            planTable.setCutDelDateNumber(dayNumber);
//                            formulaEvaluator.evaluate(cell).getDateCellValue
                        }
                        break;
                        //一部大管下料管数
                    case 20:
                        if (cellTypeEnum.equals(CellType.NUMERIC)){
                            planTable.getCutPlan().setOnebigCutNumber((int) cell.getNumericCellValue());
                        }
                        else if (cellTypeEnum.equals(CellType.STRING)){
                            int number = Integer.parseInt(cell.getStringCellValue());
                            planTable.getCutPlan().setOnebigCutNumber(number);
                        }else{
                            planTable.getCutPlan().setOnebigCutNumber(0);
                        }
                        break;
                        //一部下料加工弯管下料数
                    case 22:
                        if (cellTypeEnum.equals(CellType.NUMERIC)){
                            planTable.getCutPlan().setOneBendCutNumber((int) cell.getNumericCellValue());
                        }else if (cellTypeEnum.equals(CellType.STRING)){
                            int number = Integer.parseInt(cell.getStringCellValue());
                            planTable.getCutPlan().setOneBendCutNumber(number);
                        }else{
                            planTable.getCutPlan().setOneBendCutNumber(0);
                        }
                        break;
                    //一部下料加工直管下料数
                    case 23:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getCutPlan().setOneVerCutNumber((int) cell.getNumericCellValue());
                        else if (cellTypeEnum.equals(CellType.STRING)){
                            int number = Integer.parseInt(cell.getStringCellValue());
                            planTable.getCutPlan().setOneVerCutNumber(number);
                        }else{
                            planTable.getCutPlan().setOneVerCutNumber(0);
                        }
                        break;
                    //一部下料加工大管下料数
                    case 24:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getCutPlan().setOneBigCutNumber((int) cell.getNumericCellValue());
                        else if (cellTypeEnum.equals(CellType.STRING)){
                            int number = Integer.parseInt(cell.getStringCellValue());
                            planTable.getCutPlan().setOneBigCutNumber(number);
                        }else{
                            planTable.getCutPlan().setOneBigCutNumber(0);
                        }
                        break;
//                    //一部下料加工汇总数
//                    case 25:
//                        if (cellTypeEnum.equals(CellType.NUMERIC))
//                            planTable.getCutPlan().setOneTotalNumber((int) cell.getNumericCellValue());
//                        break;
                    //二部下料加工弯管数
                    case 27:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getCutPlan().setTwoBendNumber((int) cell.getNumericCellValue());
                        else if (cellTypeEnum.equals(CellType.STRING)){
                            int number = Integer.parseInt(cell.getStringCellValue());
                            planTable.getCutPlan().setTwoBendNumber(number);
                        }else{
                            planTable.getCutPlan().setTwoBendNumber(0);
                        }
                        break;
                        //二部下料加工直管数
                    case 28:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            planTable.getCutPlan().setTwoVerNumber((int) cell.getNumericCellValue());
                        else if (cellTypeEnum.equals(CellType.STRING)){
                            int number = Integer.parseInt(cell.getStringCellValue());
                            planTable.getCutPlan().setTwoVerNumber(number);
                        }else{
                            planTable.getCutPlan().setTwoVerNumber(0);
                        }
                        break;
//                    //二部下料加工汇总数
//                    case 29:
//                        if (cellTypeEnum.equals(CellType.NUMERIC))
//                            planTable.getCutPlan().setTwoTotalNumber((int) cell.getNumericCellValue());
//                        break;

                    //下料计划备注
                    case 31:
                        if (cellTypeEnum.equals(CellType.STRING))
                            planTable.getCutPlan().setRemark(cell.getStringCellValue());
                        break;
                    default:
                        break;
                }
            }

            if(planTable.getSerialNumber()!=null){
                //这个日期是减的！//
                Date cutFinishedDate = DateUtils2.addDate(planTable.getProcessPlan().getPlanStart(),0,0,
                        -planTable.getCutDelDateNumber(),0,0,0,0);
                //这个日期是加的！
                Date planEndDate = DateUtils2.addDate(planTable.getProcessPlan().getPlanStart(),0,0,
                        planTable.getPlanEndDateNumber(),0,0,0,0);
                planTable.getProcessPlan().setPlanEnd(planEndDate);

                planTable.getCutPlan().setFinishedDate(cutFinishedDate);

                //下料加工汇总
                CutPlan cutPlan = planTable.getCutPlan();
                planTable.getCutPlan().setOneTotalNumber(cutPlan.getOneBendCutNumber()+cutPlan.getOneVerCutNumber()+cutPlan.getOneBigCutNumber());
                planTable.getCutPlan().setTwoTotalNumber(cutPlan.getTwoBendNumber()+cutPlan.getTwoVerNumber());

                planTable.getCutPlan().setTotalCutNumber(planTable.getCutPlan().getOnebigCutNumber()+
                        planTable.getCutPlan().getOneTotalNumber()+planTable.getCutPlan().getTwoTotalNumber());

                //通过“序号+船名+批次+加工点”作为唯一数据判断，防止重复解析计划 可以在存入数据库的时候做这个

                resultList.add(planTable);
            }

        }

        workbook.close();
        return resultList;
    }


    /**根据excel公式处理下料（计划）完工时间
     *@param cellFomula cell的字符串内容：L4+28
     * @param  mod 加减格式解析："-"/"+"
     * @return 返回加减的天数*/
    public static int getCellValueFormula(String cellFomula,String mod) {
        System.out.println("fomula = "+cellFomula);
        String[]strList = cellFomula.split(mod);
        if(strList.length==2){
            String date = strList[1];
            return Integer.parseInt(date);
        }
        return  0;
    }


    /**
     * 通过“序号+船名+批次+加工点”作为唯一数据判断，防止重复解析计划
     *@param planTable 将要加到plantable的计划数据
     * @return 返回加减的天数*/
    public static PlanTable CheckRepeatPlanTableData(PlanTable planTable) {
        System.out.println("fomula = "+planTable);

        return null;
    }

}
