package com.sbot.modules.system.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/27
 */
@Data
public class SysDictExcel {

    /** 目录 **/
    @ExcelProperty(value = "目录", index = 0)
    private String catalog;

    /** 代码 **/
    @ExcelProperty(value = "代码", index = 1)
    private String code;

    /** 描述 **/
    @ExcelProperty(value = "描述", index = 2)
    private String text;

    /** 排序 **/
    @ExcelProperty(value = "排序", index = 3)
    private int sort;
}
