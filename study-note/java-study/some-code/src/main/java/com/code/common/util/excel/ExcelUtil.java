package com.code.common.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/12
 * @see: https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/write/WriteTest.java
 */
public class ExcelUtil {

    public static List read(String pathName, int sheet, int row, Class head) {
        return row == 0 ?
                EasyExcel.read(pathName, head, new ExcelListener()).sheet(sheet).doReadSync() :
                EasyExcel.read(pathName, head, new ExcelListener()).sheet(sheet).headRowNumber(row).doReadSync();
    }

    public static void write(String fileName, String sheetName, Class head, List list) {
        EasyExcel.write(fileName, head).sheet(sheetName).doWrite(list);
    }

    public static void write(String fileName, String sheetName, Class head, List list, int lineMax) throws IOException {
        int sheetNum = list.size() / lineMax;
        int lastSheetLine = list.size() % lineMax;
        sheetNum += lastSheetLine > 0 ? 1 : 0;
        ExcelWriter excelWriter = EasyExcel.write(fileName, head)/*.registerWriteHandler()*/.build();
        for (int no = 0; no < sheetNum; no++) {
            String sheetNameNo = sheetName + (no + 1);
            WriteSheet writeSheet = EasyExcel.writerSheet(no, sheetNameNo).build();
            excelWriter.write(no < sheetNum - 1 ? list.subList(no * lineMax, no * lineMax + lineMax) : list.subList(no * lineMax, list.size()), writeSheet);
        }
        excelWriter.finish();
        // 分页获取数据？
    }

    // 这样做会导致数据覆盖
    public static void write(String fileName, String sheetName, int sheetNo, Class head, List list) throws IOException {
        ExcelWriter excelWriter = EasyExcel.write(fileName, head).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, sheetName + sheetNo).build();
        excelWriter.write(list, writeSheet);
        excelWriter.finish();
    }
}
