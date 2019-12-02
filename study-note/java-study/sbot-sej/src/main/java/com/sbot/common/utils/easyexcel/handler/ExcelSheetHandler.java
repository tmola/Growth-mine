package com.sbot.common.utils.easyexcel.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.sbot.common.utils.AppContextUtil;
import com.sbot.common.utils.easyexcel.ExcelUtil;
import com.sbot.modules.system.entity.SysDict;
import com.sbot.modules.system.entity.excel.SysUserExcel;
import com.sbot.modules.system.services.SysDictService;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.*;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
public class ExcelSheetHandler<T> implements SheetWriteHandler {

    private T obj;

    private SysDictService dictService = (SysDictService) AppContextUtil.getBeanByName("SysDictService");


    public ExcelSheetHandler(T obj) {
        this.obj = obj;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {


        if (obj instanceof SysUserExcel) {
            // 性别
            List<SysDict> sexList = dictService.getDictList("sex");

            if (null != sexList && !sexList.isEmpty()) {
                String[] sexArr = new String[sexList.size()];
                int i = 0;
                for (SysDict dict : sexList) {
                    sexArr[i++] = dict.getText();
                }
                CellRangeAddressList sexCell = new CellRangeAddressList(1, 1000, 2, 2);
                DataValidationHelper sexHelper = writeSheetHolder.getSheet().getDataValidationHelper();
                DataValidationConstraint sexConstraint = sexHelper.createExplicitListConstraint(sexArr);
                DataValidation sexValidation = sexHelper.createValidation(sexConstraint, sexCell);
                writeSheetHolder.getSheet().addValidationData(sexValidation);

//                String[] shengf = new String[]{"四川", "广东"};
//                String[] shiq1 = new String[]{"成都", "泸州"};
//                String[] xianq1 = new String[]{"高新区", "新都区"};
//                String[] xianq2 = new String[]{"合江县", "叙永县"};
//                String[] shiq2 = new String[]{"广州", "揭阳"};
//                String[] xianq3 = new String[]{"广州中", "广州东"};
//                String[] xianq4 = new String[]{"潮汕", "揭阳小"};
//                String[] xianq5 = new String[]{"潮汕ds", "潮汕zs"};
//                Map<String, String[]> sexmap = new HashMap<>();
//                sexmap.put(shengf[0], shiq1);
//                sexmap.put(shengf[1], shiq2);
//                sexmap.put(shiq1[0], xianq1);
//                sexmap.put(shiq1[1], xianq2);
//                sexmap.put(shiq2[0], xianq3);
//                sexmap.put(shiq2[1], xianq4);
//                sexmap.put(xianq4[1], xianq5);
//                ExcelUtil.createCascadeDropdownBoxOnSheet(writeSheetHolder.getSheet(),
//                        "dict", shengf, sexmap, 2, 1, 2, 4, 5);
//                String[] ifx = new String[]{"是", "否"};
//                Map map = new HashMap();
//                map.put(ifx[0], null);
//                map.put(ifx[1], null);
//                ExcelUtil.createCascadeDropdownBoxOnSheet(writeSheetHolder.getSheet(),
//                        "dict2", ifx, map, 2, 6);
//
            }

        }
    }
}