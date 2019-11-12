package com.code.common.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/12
 */
@Data
@Slf4j
public class ExcelListener extends AnalysisEventListener {

    private List dataList = new ArrayList<>();

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        log.info("错误日志:{}", exception.getCause());
    }


    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(o));
        dataList.add(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }

}
