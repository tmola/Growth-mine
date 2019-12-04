package com.uniform.modules.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/22
 */
@Data
public class SysUserExcel {

    /**
     * 账号名称
     **/
    @ExcelProperty(value = "账号名称", index = 0)
    private String name;

    /**
     * 真实姓名
     **/
    @ExcelProperty(value = "真实姓名", index = 1)
    private String realName;


    @ExcelProperty(value = {"性别"}, index = 2)
    private String sex;

    /**
     * 出生日期
     **/
    @ExcelProperty(value = {"出生日期"}, index = 3)
    private Date borndate;
}
