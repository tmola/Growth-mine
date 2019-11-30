package com.sbot.common.utils.easyexcel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/28
 */
public class ExcelUtil {
    // Name:A name is a meaningful shorthand that makes it easier to understand the purpose of a cell reference, constant or a formula.
    // 名称：是一种有意义的速记，它使理解单元格引用、常量或公式的目的变得更容易。
    // see；http://poi.apache.org/apidocs/dev/org/apache/poi/ss/usermodel/Name.html

    /**
     * 在row行的第startCol列新增一个内容为value的单元格
     *
     * @param row
     * @param startCol 第几列（从1开始）
     * @param value    值
     * @return
     */
    public static Cell createCell(Row row, int startCol, String value) {
        Cell cell = row.createCell(--startCol);
        cell.setCellValue(value);
        return cell;
    }

    /**
     * 在row行的第startCol列新增n（取决于valus）个单元格
     *
     * @param row
     * @param startCol 从col列开始（col>=1）
     * @param values   值数组
     */
    public static void createCells(Row row, int startCol, String... values) {
        if (null == values || 0 == values.length)
            return;
        startCol--;
        for (int i = startCol; i < values.length + startCol; i++) {
            String content = values[i - startCol];
            createCell(row, i + 1, content);
        }
        return;
    }

    /**
     * 创建一行数据
     *
     * @param sheet    sheet
     * @param startRow 开始行（从1开始）
     * @param startCol 开始列（从1开始）
     * @param contents 每行内容
     */
    public static Row createRow(Sheet sheet, int startRow, int startCol, String... contents) {
        if (startRow < 1 || startCol < 1) return null;
        Row row = sheet.createRow(--startRow);
        if (null == contents || 0 == contents.length)
            return row;
        for (int i = --startCol; i < contents.length + startCol; i++) {
            String content = contents[i - startCol];
            createCell(row, i + 1, content);
        }
        return row;
    }

    /**
     * 创建多行数据
     *
     * @param sheet       sheet
     * @param startRow    开始行（从1开始）
     * @param rowNum      行数
     * @param stratCol    开始列（从1开始）
     * @param contentList 多行内容列表
     */
    public static Sheet createRows(Sheet sheet, int startRow, int stratCol, int rowNum, String[]... contentList) {

        boolean haveContent = true;
        if (null == contentList || 0 == contentList.length)
            haveContent = false;
        for (int i = --startRow; i < startRow + rowNum; i++) {
            if (!haveContent || contentList.length < i) {
                break;
            } else createRow(sheet, i, --stratCol, contentList[i]);
        }
        return sheet;
    }

    /**
     * 创建带数据的sheet
     *
     * @param workbook
     * @param sheetName sheet名称
     * @param title     标题数组
     * @param rowList   内容数组列表
     */
    public static void createSheetWithData(Workbook workbook, String sheetName, boolean hide, String title[], String[]... rowList) {
        Sheet sheet = workbook.createSheet(sheetName);
        workbook.setSheetHidden(workbook.getSheetIndex(sheetName), hide);
        createRow(sheet, 0, 0, title);
        createRows(sheet, 1, 0, null == rowList ? 0 : rowList.length, rowList);
    }

    /**
     * 计算单元格范围位置坐标
     *
     * @param rowOffset 行偏移量(从1计数，第几行开始)
     * @param startCol  从第几列开始
     * @param colNum    总共的列数
     * @return $x$y$m$n: y行x列 到 n行m列
     */
    public static String getCellsRange(int rowOffset, int startCol, int colNum) {
//        if(colNum<1)return "";
        startCol--;
        StringBuffer end = new StringBuffer();
        for (int i = 0; i < (colNum - 1) / 26; i++)
            end.append("A");
        end.append((char) ('A' + (colNum - 1) % 26));
        return "$" + (char) ('A' + startCol) + "$" + rowOffset + ":$" + end.toString() + "$" + rowOffset;
    }

    /**
     * 计算一个单元格位置坐标
     *
     * @param rowOffset 第几行（从1开始）
     * @param colOffset 第几列（从1开始）
     * @return $col$row
     */
    public static String getCellRange(int rowOffset, int colOffset) {
        if (rowOffset < 1 || colOffset < 1) return "";
        int colIndex = colOffset - 1;
        StringBuffer end = new StringBuffer();
        for (int i = 0; i < colIndex / 26; i++)
            end.append("A");
        end.append((char) ('A' + colIndex % 26));
        return "$" + end.toString() + "$" + rowOffset;
    }

    /**
     * 计算一行单元格位置坐标
     *
     * @param rowOffset 第几行（从1开始）
     * @param colOffset 第几列（从1开始）
     * @param cellNum   单元格数（列数）
     * @return $col1$row1:$col2$row2
     */
    public static String getRowRange(int rowOffset, int colOffset, int cellNum) {
        return getCellRange(rowOffset, colOffset) + ":" + getCellRange(rowOffset, colOffset + cellNum - 1);
    }

    /**
     * 获取一列单元格位置
     *
     * @param rowOffset 第几行（从1开始）
     * @param colOffset 第几列（从1开始）
     * @param cellNum   单元格数（列数）
     * @return $col1$row1:$col2$row2
     */
    public static String getColRange(int rowOffset, int colOffset, int cellNum) {
        return getCellRange(rowOffset, colOffset) + ":" + getCellRange(rowOffset + cellNum - 1, colOffset);
    }

    /**
     * 添加数据有效性验证
     *
     * @param sheet
     * @param firstRow    第几行开始
     * @param lastRow     第几行结束
     * @param firstColumn 第几列开始
     * @param lastColumn  第几列结束
     * @param nameName    ‘名称’名字
     */
    public static void addDataValidationOnSheet(Sheet sheet, int firstRow, int lastRow, int firstColumn, int lastColumn, String nameName) {

        DataValidationHelper oilHelper = sheet.getDataValidationHelper();
        /*设置需要验证的单元格区域*/
        CellRangeAddressList regions = new CellRangeAddressList(--firstRow, --lastRow, --firstColumn, --lastColumn);
        /*设置数据范围*/
        DataValidationConstraint constraint = oilHelper.createFormulaListConstraint(nameName);
        /*创建数据有效性验证*/
        DataValidation validation = oilHelper.createValidation(constraint, regions);
//        validation.createErrorBox("error", errmsg);
        /*sheet添加数据有效性验证*/
        sheet.addValidationData(validation);
    }


    /**
     * 在sheet创建多级级联下拉框
     *
     * @param sheet
     * @param dictSheetName 配置数据字典的隐藏sheet的名称
     * @param roots         最上层的下拉集合
     * @param map           下拉集合map
     * @param startRow      从第几行开始设置
     * @param cols          设置对应的列（按层级排序）
     */
    public static void createCascadeDropdownBoxOnSheet(Sheet sheet, String dictSheetName,
                                                       String[] roots, Map<String, String[]> map,
                                                       int startRow, int... cols) {
        Workbook workbook = sheet.getWorkbook();
        Sheet dictSheet = workbook.createSheet(dictSheetName);
        workbook.setSheetHidden(workbook.getSheetIndex(dictSheetName), true);
        Row row = createRow(dictSheet, 1, 1, "&&");// A1 = &&
        createCells(row, 2, roots); // B1~Bx = roots
        Name name = workbook.createName();
        String root = dictSheetName + "_root";
        name.setNameName(root);
        String rootFormula = dictSheetName + "!" + getRowRange(1, 2, roots.length);
        name.setRefersToFormula(rootFormula);

        // 从第2行开始添加各个父节点的名称管理器
        int i = 2;
//        String[] forimulaArr = new String[map.size()];
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            List<String> list = new ArrayList();
            list.add(entry.getKey());
            if (entry.getValue() != null)
                for (String iter : entry.getValue())
                    list.add(iter);
            createRow(dictSheet, i, 1, (String[]) list.toArray(new String[list.size()])); //Ai-xj = string[i-1]
            name = workbook.createName();
            name.setNameName(entry.getKey());  /*key不可重复,将父区域名作为key*/
            String forimula = dictSheetName + "!" + getCellsRange(i, 2, list.size());
//            forimulaArr[i - 2] = forimula;
            name.setRefersToFormula(forimula);
            ++i;
        }
        addDataValidationOnSheet(sheet, startRow, 10000, cols[0], cols[0], root);
        for (int j = 1; j < cols.length; j++) {
            String s = getCellRange(2, cols[j - 1]);
            int inde = s.lastIndexOf("$");
            String colx = s.substring(0, s.lastIndexOf("$"));
            String rowx = s.substring(s.lastIndexOf("$") + 1);
            s = colx + rowx;
            if (j > map.size()) break;
            addDataValidationOnSheet(sheet, startRow, 10000, cols[j], cols[j], "INDIRECT(" + s + ")");
        }
    }

    public static Map<String, String[]> formatMapValues(List<List<String>> lists) {
        Map<String, String[]> map = new HashMap<>();
        for (List<String> list : lists) {
            if (list != null && list.size() > 0)
                map.put(list.get(0), list.toArray(new String[list.size()]));
        }
        return map;
    }
}
