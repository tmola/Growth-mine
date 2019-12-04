package com.uniform.modules.services;


import com.uniform.common.base.BaseService;
import com.uniform.modules.services.entity.SysExcel;

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
