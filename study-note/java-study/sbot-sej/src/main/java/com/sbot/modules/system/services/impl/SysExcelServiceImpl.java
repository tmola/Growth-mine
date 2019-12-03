package com.sbot.modules.system.services.impl;

import com.sbot.common.base.BaseServiceOperator;
import com.sbot.common.enums.ResultCode;
import com.sbot.common.exception.ProjectException;
import com.sbot.common.utils.OptRetMapUtil;
import com.sbot.common.utils.QueryStrategy;
import com.sbot.common.utils.ToolUtil;
import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.SysExcel;
import com.sbot.modules.system.entity.SysUser;
import com.sbot.modules.system.repository.SysExcelRepository;
import com.sbot.modules.system.services.SysExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/2
 */
@Service("SysExcelService")
public class SysExcelServiceImpl implements SysExcelService {
    @Autowired
    private SysExcelRepository excelRepository;

    @Override
    public Map select(QueryVO<SysExcel> queryVO) throws Exception {
        QueryStrategy queryStrategy = new QueryStrategy();
        List<Sort.Order> orders = new ArrayList<>();
//        orders.add(new Sort.Order(Sort.Direction.ASC, "sort"));
        return BaseServiceOperator.select(excelRepository, queryVO, queryStrategy, orders);
    }

    @Override
    public Map save(List<SysExcel> records) throws Exception {
        return BaseServiceOperator.save(excelRepository, records);
    }

    @Override
    public Map deleteByIds(List<String> ids) throws Exception {
        return null;
    }


    @Override
    public SysExcel getByName(String entityName, String excelName) {
        return excelRepository.getByName(entityName,excelName);
    }

    @Override
    public SysExcel getByName(String entityName) {
        return excelRepository.getByName(entityName);
    }
}
