package com.code.modules.demo.entity.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/12
 */
@Data
public class TestExcelModel {

    @ExcelProperty(value = {"序号"}, index = 0)
    private Integer num;

    @ExcelProperty(value = {"姓名"}, index = 1)
    private String name;

    @ExcelProperty(value = {"联系方式", "电话"}, index = 2)
    private String phone;

    @ExcelProperty(value = {"联系方式", "邮箱"}, index = 3)
    private String email;
}
