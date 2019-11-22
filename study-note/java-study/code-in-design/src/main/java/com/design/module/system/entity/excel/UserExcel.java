package com.design.module.system.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.design.common.annotation.dictAnnotation.TranDict;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/22
 */
@Data
public class UserExcel {

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
    private String sexDictText;

    /**
     * 出生日期
     **/
    @ExcelProperty(value = {"出生日期"}, index = 3)
    private Date borndate;
}
