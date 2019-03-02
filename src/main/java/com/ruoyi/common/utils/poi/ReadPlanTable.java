package com.ruoyi.common.utils.poi;/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/3/2
 * Time: 9:02
 */

/**
 *@ClassName: ReadPlanTable
 *@Description: TODO
 *@Author: Ricardo
 *@Date: 2019/3/2 9:02
 **/
public class ReadPlanTable {


//    /**
//     * @param file
//     * @throws EncryptedDocumentException
//     * @throws IOException
//     * @throws InvalidFormatException
//     */
//    private List<PlanTable> readExcel(MultipartFile file)
//            throws EncryptedDocumentException, IOException, InvalidFormatException {
//        List<PlanTable> resultList = new ArrayList<>();
//        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//        // 同时支持ecxel-2003/2007/2010
//        // File excelFile = new File("D:/test.xlsx");
//        // FileInputStream is = new FileInputStream(excelFile);
//        String originalName = file.getOriginalFilename();
//        String planTableName = originalName.substring(0, originalName.indexOf('.'));
//        // 这种方式 Excel 2003/2007/2010都是可以处理的
//        Workbook workbook = WorkbookFactory.create(file.getInputStream());
//        int sheetCount = workbook.getNumberOfSheets();
//        // 遍历每个sheet
//
//        Sheet sheet = workbook.getSheetAt(0);
//        int rowCount = sheet.getPhysicalNumberOfRows();// 获取总行数
//        int planId = planTableService.selectMaxId() + 1;
//        for (int r = 3; r < rowCount; r++) {
//            Row row = sheet.getRow(r);
//            int cellCount = row.getPhysicalNumberOfCells();
//            PlanTable planTable = new PlanTable();
//            planTable.setPlanName(planTableName);
//            planTable.setUpldateTime(new Date());
//            for (int c = 0; c < cellCount; c++) {
//                Cell cell = row.getCell(c);
//                if (cell == null)
//                    continue;
//                switch (c) {
//                    case 0:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC)){
//                            planTable.setSerialNum((int) cell.getNumericCellValue());
//                        }
//                        else
//                            continue;
//                        break;
//                    case 1:
//                        if (cell.getCellTypeEnum().equals(CellType.STRING))
//                            planTable.setShipName(cell.getStringCellValue());
//                        break;
//                    case 2:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setBatch(new Integer((int) cell.getNumericCellValue()).toString());
//                        else
//                            planTable.setBatch(cell.getStringCellValue());
//                        break;
//                    case 3:
//                        if (cell.getCellTypeEnum().equals(CellType.STRING))
//                            planTable.setBatchDescrip(cell.getStringCellValue());
//                        break;
//                    case 4:
//                        if (cell.getCellTypeEnum().equals(CellType.STRING))
//                            planTable.setSections(cell.getStringCellValue());
//                        break;
//                    case 5:
//                        if (cell.getCellTypeEnum().equals(CellType.STRING))
//                            planTable.setStocks(cell.getStringCellValue());
//                        break;
//                    case 6:
//                        if (cell.getCellTypeEnum().equals(CellType.STRING))
//                            planTable.setProcessPoint(cell.getStringCellValue());
//                        break;
//                    case 7:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setNumber((int) cell.getNumericCellValue());
//                        break;
//                    case 8:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setLightBodyPip((int) cell.getNumericCellValue());
//                        break;
//                    case 11:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setPlanStart(cell.getDateCellValue());
//                        break;
//                    case 12:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setPlanEnd(cell.getDateCellValue());
//                        break;
//                    case 14:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setActualStart(cell.getDateCellValue());
//                        break;
//                    case 15:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setActuralEnd(cell.getDateCellValue());
//                        break;
//                    case 16:
//                        System.out.println(r + "/" + rowCount);
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setRemark(String.valueOf(cell.getNumericCellValue()));
//                        else
//                            planTable.setRemark(cell.getStringCellValue());
//                        break;
//                    case 17:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setSentPicTime(cell.getDateCellValue());
//                        break;
//                    case 19:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setPlanCutFinish(cell.getDateCellValue());
//                        break;
//                    case 20:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setOneBcutNum((int) cell.getNumericCellValue());
//                        else
//                            planTable.setOneBcutNum(stringToNum(cell.getStringCellValue()));
//                        break;
//                    case 22:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setOneBendCut((int) cell.getNumericCellValue());
//                        else
//                            planTable.setOneBendCut(stringToNum(cell.getStringCellValue()));
//                        break;
//                    case 23:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setOneVerCut((int) cell.getNumericCellValue());
//                        else
//                            planTable.setOneVerCut(stringToNum(cell.getStringCellValue()));
//                        break;
//                    case 24:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setOneBigCut((int) cell.getNumericCellValue());
//                        else
//                            planTable.setOneBigCut(stringToNum(cell.getStringCellValue()));
//                        break;
//                    case 27:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setTwoSpeBendCut((int) cell.getNumericCellValue());
//                        else
//                            planTable.setTwoSpeBendCut(stringToNum(cell.getStringCellValue()));
//                        break;
//                    case 28:
//                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                            planTable.setTwoSpeVerCut((int) cell.getNumericCellValue());
//                        else
//                            planTable.setTwoSpeVerCut(stringToNum(cell.getStringCellValue()));
//                        break;
//                    case 31:
//                        if (cell.getCellTypeEnum().equals(CellType.STRING))
//                            planTable.setCutRemark(cell.getStringCellValue());
//                        break;
//                    default:
//                        break;
//                }
//            }
//            planTable.setPlanId(planId);
//            if(planTable.getSerialNum()!=null)
//                resultList.add(planTable);
//        }
//
//        workbook.close();
//        return resultList;
//    }

}
