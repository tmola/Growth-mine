package com.uniform.modules.system.services;


import com.uniform.common.base.BaseService;
import com.uniform.modules.system.entity.SysExcel;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/2
 */
public interface SysExcelService extends BaseService<SysExcel> {
    SysExcel getByName(String entityName, String excelName);

    SysExcel getByName(String entityName);
}
