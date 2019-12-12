package com.uniform.common.utils.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;

import com.uniform.common.annotation.DictField;
import com.uniform.common.enums.ResultCode;
import com.uniform.common.exception.BusinessException;
import com.uniform.common.utils.AppContextUtil;
import com.uniform.common.utils.FileUtil;
import com.uniform.common.utils.ToolUtil;
import com.uniform.common.utils.easyexcel.handler.ExcelSheetHandler;
import com.uniform.common.utils.easyexcel.listener.ExcelListener;
import com.uniform.common.vo.QueryVO;
import com.uniform.common.vo.ResultVO;
import com.uniform.modules.system.services.SysDictService;
import com.uniform.modules.system.services.SysExcelService;
import com.uniform.modules.system.entity.SysExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
@Component
public class EasyExcelUtil {
    public static EasyExcelUtil easyExcelUtil;

    public static final String selectMethodName = "select";
    public static final String saveMethodName = "save";

    @Autowired
    private SysExcelService excelService;

    @Autowired
    private SysDictService dictService;

    @PostConstruct
    public void init() {
        easyExcelUtil = this;
        easyExcelUtil.excelService = this.excelService;
        easyExcelUtil.dictService = this.dictService;
    }


    /**
     * 读取并解析Excel文档
     *
     * @param pathName 文件路径
     * @param sheet    指定哪一个表格
     * @param row      指定开始行
     * @param head     表头
     */
    public static List read(String pathName, int sheet, int row, Class head) {
        return row == 0 ?
                EasyExcel.read(pathName, head, new ExcelListener()).sheet(sheet).doReadSync() :
                EasyExcel.read(pathName, head, new ExcelListener()).sheet(sheet).headRowNumber(row).doReadSync();
    }

    /**
     * 写入Excel文档
     *
     * @param fileName  文件名
     * @param sheetName sheet名
     * @param head      表头
     * @param list      数据列表
     */
    public static void write(String fileName, String sheetName, Class head, List list) {
        EasyExcel.write(fileName, head).sheet(sheetName).doWrite(list);
    }

    /**
     * 导出数据为xlsx文档
     *
     * @param filename   导出文档名
     * @param sheetName  导出sheet名
     * @param entityName “实体名”
     * @param excelName  “Excel模板实体名”
     * @param query      查询条件
     */
    public static void exportData(String filename,
                                  String sheetName,
                                  String entityName,
                                  String excelName,
                                  QueryVO query) throws Exception {
        SysExcel sysExcel = easyExcelUtil.excelService.getByName(entityName, excelName);
        if (sysExcel == null)
            throw new BusinessException(ResultCode.faild);
        Class excelClazz = Class.forName(sysExcel.getExcelClazz());
        Object excelTemplate = excelClazz.newInstance();

        Object service = AppContextUtil.getBeanByName(sysExcel.getDealServiceName());
        Class entityClass = Class.forName(sysExcel.getEntityClazz());
        query.setTerms(ToolUtil.Json2Object(query.getTerms(), entityClass));
        Method method = service.getClass().getMethod(selectMethodName, QueryVO.class);
        Object ret = method.invoke(service, query);
        List datas = (List) ((Map) ret).get("list");
        if (datas == null || datas.size() == 0)
            throw new BusinessException(ResultCode.faild);
        List list = list2ExcelList(datas, excelTemplate);
        File file = FileUtil.createRomdonNameFile(FileUtil.templatePath, "xlsx");
        write(file.getAbsolutePath(), sheetName, excelClazz, list);
        FileUtil.downloadFile(file.getAbsolutePath(), filename);
    }

    /**
     * 导入Excel文档数据
     *
     * @param file       Excel文件
     * @param entityName “实体”名
     * @param excelName  “Excel实体”名
     */
    public static ResultVO importData(MultipartFile file, String entityName, String excelName) throws Exception {
        SysExcel sysExcel = easyExcelUtil.excelService.getByName(entityName, excelName);
        if (sysExcel == null)
            throw new BusinessException(ResultCode.faild);
        Class entityClazz = Class.forName(sysExcel.getEntityClazz());
        Class excelClazz = Class.forName(sysExcel.getExcelClazz());
        String filePath = FileUtil.uploadFile(file);
        List<Object> datas = read(filePath, sysExcel.getBeginSheet() - 1, sysExcel.getBeginRow() - 1, excelClazz);
        FileUtil.deleteFile(filePath);
        Object service = AppContextUtil.getBeanByName(sysExcel.getDealServiceName());
        Object entityObject = entityClazz.newInstance();
        Method method = service.getClass().getMethod(saveMethodName, List.class);
        return ResultVO.successful(method.invoke(service, excelList2List(datas,entityObject)));
    }

    /**
     * 导出xlsx数据模板
     */
    public static void exportTemplate(String filename, String sheetName, String entityName, String excelName) throws Exception {
        SysExcel sysExcel = easyExcelUtil.excelService.getByName(entityName, excelName);
        if (sysExcel == null)
            throw new BusinessException(ResultCode.faild);
        Class excelClass = Class.forName(sysExcel.getExcelClazz());
        File file = FileUtil.createRomdonNameFile(FileUtil.templatePath, "xlsx");
        EasyExcel.write(file.getAbsolutePath(), excelClass)
                .registerWriteHandler(new ExcelSheetHandler(excelClass.newInstance()))
                .sheet(sheetName)
                .doWrite(null);
        FileUtil.downloadFile(file.getAbsolutePath(), filename);
    }

    /**
     * 将T类型的数据列表转换为S类型的Excel列表
     *
     * @param s
     * @param list
     * @return java.util.List<java.lang.Object>
     */
    public static <T, S> List<Object> list2ExcelList(List<T> list, S s) throws IllegalAccessException, InstantiationException {
        List<Object> newList = new ArrayList<>();
        Class<?> clazz1 = s.getClass();
        Field[] fields1 = clazz1.getDeclaredFields();
        if (null != list && !list.isEmpty()) {
            for (T data : list) {
                Object object = clazz1.newInstance();
                Class<?> clazz = data.getClass();
                Field[] fields2 = clazz.getDeclaredFields();
                for (int i = 0; i < fields1.length; i++) {
                    Field field1 = fields1[i];
                    for (int j = 0; j < fields2.length; j++) {
                        Field field2 = fields2[j];
                        String f1 = field1.getName();
                        String f2 = field2.getName();
                        if ((f2.equals(f1 + "DictText")) || (field2.getAnnotation(DictField.class) == null && f1.equals(f2))) {
                            try {
                                field1.setAccessible(true);
                                field2.setAccessible(true);
                                field1.set(object, field2.get(data));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
                newList.add(object);
            }
        }
        return newList;
    }

    public static <T> List<T> excelData2EntityData(List<Object> excelList, T entity) throws Exception {
        JSONArray jsonArray = new JSONArray(excelList);
        List<T> records = new ArrayList<>();
        Class entityClass = entity.getClass();
        return null;
    }


    public static <T> List<T> excelList2List(List<Object> list, T t) throws Exception {
        JSONArray jsonArray = new JSONArray(list);
        List<T> records = new ArrayList<>();
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<String> dictCodes = new ArrayList<>();
        List<Method> getmethods = new ArrayList<>();
        List<Method> setmethods = new ArrayList<>();
        for (int j = 0; j < fields.length; j++) {
            Field field = fields[j];
            if (field.getAnnotation(DictField.class) != null) {
                DictField tranDict = field.getAnnotation(DictField.class);
                dictCodes.add(tranDict.dict());
                getmethods.add(clazz.getDeclaredMethod("get" + ToolUtil.upperFirst(field.getName())));
                setmethods.add(clazz.getDeclaredMethod("set" + ToolUtil.upperFirst(field.getName()), String.class));
            }
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            T record = (T) jsonArray.getJSONObject(i).toJavaObject(clazz);
            for (int j = 0; j < getmethods.size(); j++) {
                String dictCode = easyExcelUtil.dictService.getCode(dictCodes.get(j), (String) getmethods.get(j).invoke(record));
                setmethods.get(j).invoke(record, dictCode);
            }
            records.add(record);
        }
        return records;
    }
}
