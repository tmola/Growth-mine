package com.code.common.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.code.common.util.DateUtil;
import com.code.common.util.HttpUtil;
import com.code.common.util.ObjectUtil;
import com.code.common.util.ToolUtil;
import com.code.common.util.result.Result;
import com.code.modules.dict.entity.model.TestDict;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
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

    //    @Value("${project.template.file-path}") //使用@value 的类中必须被@Service 或@Component注解
    private static String templatePath = "H:\\\\Growth-mine\\\\template";
    private static String uploadPath = "H:\\\\Growth-mine\\\\upload";

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
//
//    // 这样做会导致数据覆盖
//    public static void write(String fileName, String sheetName, int sheetNo, Class head, List list) throws IOException {
//        ExcelWriter excelWriter = EasyExcel.write(fileName, head).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, sheetName + sheetNo).build();
//        excelWriter.write(list, writeSheet);
//        excelWriter.finish();
//    }

    public static String createExcelFile() {
        String fileName = ObjectUtil.randomID() + ".xlsx";
        String fileURL = templatePath + File.separator + fileName;
        File filePath = new File(templatePath);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        File file = new File(fileURL);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.getAbsolutePath();
    }


    public static boolean deleteFile(String absolutePath) {
        File file = new File(absolutePath);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    /**
     * 文件下载（单个）
     */
    public static void downloadFile(String sourceName, String rename) throws IOException {
        File sourceFile = new File(sourceName);
        if (!sourceFile.exists() || !sourceFile.isFile()) {
            return;
        }
        ServletContext servletContext = HttpUtil.getRequest().getServletContext();
        //获取文件的MimeType类型
        String mimeType = servletContext.getMimeType(sourceName);
        //设置文件的输出类型
        HttpUtil.getResponse().setContentType(mimeType);
        // 确定文件是内嵌或弹出下载框
        String filename = URLEncoder.encode(rename + ToolUtil.getFileSuffix(sourceName), HttpUtil.ENCODING_GBK);
        HttpUtil.getResponse().addHeader(HttpUtil.CONTENT_DISCRIPTION, HttpUtil.ATTACHMENT_FILENAME_EQ + filename);
        // 通过Response域，创建Servlet的输出流，输出文件
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(sourceFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        OutputStream os = HttpUtil.getResponse().getOutputStream();
        int size = bis.read(buffer);
        while (size != -1) {
            os.write(buffer, 0, size);
            size = bis.read(buffer);
        }
        //关流，response获得流会自动关闭，因此也可以不用手动关
        os.close();
        bis.close();
        fis.close();
        ExcelUtil.deleteFile(sourceName);
    }

    /**
     * 文件上传（单个），保存格式：uploadPath + /suffix/yyyy-MM-dd-xxxxxxxxxxxxxx.suffix
     */
    public static String uploadFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty())
            return null;
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = ToolUtil.getFileSuffix(originalFilename);
        String filePath = ToolUtil.appendFile(uploadPath, suffix.replace(".", ""));
        File file = new File(filePath);
        if (!file.exists())
            file.mkdir();
        String newFilename = ObjectUtil.randomID_35() + suffix;
        String fileURL = ToolUtil.appendFile(filePath, newFilename);
        File dest = new File(fileURL);
        multipartFile.transferTo(dest);
        return fileURL;
    }

    /**
     * 导出数据为xlsx文档
     */
    public static <T> void exportDataByExcelFile(List<T> list, String filename,
                                                 String sheetName, Integer lineMax) throws IOException {

        if (list == null || list.size() == 0)
            return;
        String file = ExcelUtil.createExcelFile();
        ExcelUtil.write(file, sheetName, list.get(0).getClass(), list, 1);
        ExcelUtil.downloadFile(file, filename);
    }

//    /**
//     * 从xlsx文档导入数据
//     */
//    public static void importDataFromExcelFile(MultipartFile file,
//                                               HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//    }

}
