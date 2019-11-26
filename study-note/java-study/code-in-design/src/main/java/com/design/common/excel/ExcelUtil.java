package com.design.common.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.design.common.listener.ExcelListener;
import com.design.common.util.HttpUtil;
import com.design.common.util.ObjectUtil;
import com.design.common.util.ToolUtil;
import com.design.common.util.result.ApiResult;
import com.design.module.system.entity.excel.UserExcel;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSON;

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
    private static final String excelPaket = "com.design.module.system.entity.excel.";

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
            file.mkdirs();
        String newFilename = ObjectUtil.randomID_35() + suffix;
        String fileURL = ToolUtil.appendFile(filePath, newFilename);
        File dest = new File(fileURL);
        if (!dest.exists())
            dest.createNewFile();
        multipartFile.transferTo(dest);
        return dest.getAbsolutePath();
    }

    /**
     * 导出数据为xlsx文档
     */
    public static <T> void exportDataByExcelFile(List<T> list, String filename,
                                                 String sheetName, Integer lineMax) throws IOException {

        if (list == null || list.size() == 0)
            return;
        String file = ExcelUtil.createExcelFile();
        write(file, sheetName, list.get(0).getClass(), list, lineMax);
        downloadFile(file, filename);
    }

    /**
     * 导出xlsx数据模板
     */
    public static <T> void exportTemplateByExcelFile(String excelClassName, String sheetName, String filename) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName(excelPaket + excelClassName);
        String file = ExcelUtil.createExcelFile();
        EasyExcel.write(file, clazz)
                .registerWriteHandler(new ExcelSheetHandler(clazz.newInstance()))
                .sheet(sheetName)
                .doWrite(null);
        downloadFile(file, filename);
    }

    public static ApiResult importDataFromExcelFile(MultipartFile file, String excelClassName,String serviceName,
                                                  int sheet, int row) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Object> datas = analyzingDataFromExcelFile(file, excelClassName, sheet, row);
        Object service = ApplicationContextUtil.getSpringBean(serviceName);
        Method method = service.getClass().getMethod("uploadExcelData", List.class);
        return ApiResult.success(method.invoke(service, datas));
    }

    /**
     * 从xlsx文档解析数据
     */
    private static List analyzingDataFromExcelFile(MultipartFile file, String excelClassName,
                                                  int sheet, int row) throws IOException, ClassNotFoundException {
        Class excelClass = Class.forName(excelPaket + excelClassName);
        String filePath = ExcelUtil.uploadFile(file);
        List<Object> list;
        if (0 == row)
            list = EasyExcel.read(filePath, excelClass, new ExcelListener()).sheet(sheet).doReadSync();
        else
            list = EasyExcel.read(filePath, excelClass, new ExcelListener()).sheet(sheet).headRowNumber(row).doReadSync();
//        ExcelUtil.deleteFile(filePath);
        return list;
    }


}
