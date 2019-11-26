package com.sbot.common.utils.easyexcel.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.sbot.common.utils.AppContextUtil;
import com.sbot.modules.system.entity.SysDict;
import com.sbot.modules.system.entity.excel.UserExcel;
import com.sbot.modules.system.services.SysDictService;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
public class ExcelSheetHandler<T> implements SheetWriteHandler {

    private T obj;

    private SysDictService dictService = (SysDictService) AppContextUtil.getBeanByName("dictService");


    public ExcelSheetHandler(T obj) {
        this.obj = obj;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

        if (obj instanceof UserExcel) {
            // 性别
            List<SysDict> sexList = dictService.getDictList("sex");
            String[] sexArr = new String[sexList.size()];
            if (null != sexList && !sexList.isEmpty()) {
                int i=0;
                for (SysDict dict:sexList) {
                    sexArr[i++] = dict.getText();
                }
                CellRangeAddressList sexCell = new CellRangeAddressList(1,1000, 2,2);
                DataValidationHelper sexHelper = writeSheetHolder.getSheet().getDataValidationHelper();
                DataValidationConstraint sexConstraint = sexHelper.createExplicitListConstraint(sexArr);
                DataValidation  sexValidation =sexHelper.createValidation(sexConstraint, sexCell);
                writeSheetHolder.getSheet().addValidationData(sexValidation);
            }

        }
    }
}