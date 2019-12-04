package com.uniform.common.base;

import com.sbot.common.enums.ResultCode;
import com.sbot.common.exception.ProjectException;
import com.sbot.common.utils.OptRetMapUtil;
import com.sbot.common.utils.QueryStrategy;
import com.sbot.common.utils.ToolUtil;
import com.sbot.common.vo.QueryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.o
 * @date 2019/12/2 21:06
 */
public class BaseServiceOperator {
    public static <T> Map save(BaseRepository<T> repository, List<T> records) throws Exception {
        if (ToolUtil.isEmpty(records)) return OptRetMapUtil.optError("保存的数据不能为空");
        Integer success = 0;
        Integer field = 0;
        Class clz = records.get(0).getClass();
        Method getId = clz.getMethod("getId");
        Method setId = clz.getMethod("setId", String.class);
        Method setDelFlag = clz.getMethod("setDelFlag", Integer.class);
        Method setCreateTime = clz.getMethod("setCreateTime", Date.class);
        Method setCreateUser = clz.getMethod("setCreateUser", String.class);
        Method setModifyTime = clz.getMethod("setModifyTime", Date.class);
        Method setModifyUser = clz.getMethod("setModifyUser", String.class);
        List<T> fieldList = new ArrayList<>();
        for (T record : records) {
            if (Objects.nonNull(setId))
                if (ToolUtil.isEmpty(getId.invoke(record))) {
                    if (Objects.nonNull(setId))
                        setId.invoke(record, ToolUtil.randomID35());
                    if (Objects.nonNull(setDelFlag))
                        setDelFlag.invoke(record, 0);
                    if (Objects.nonNull(setCreateTime))
                        setCreateTime.invoke(record, new Date());
                } else {
                    if (Objects.nonNull(setModifyTime))
                        setModifyTime.invoke(record, new Date());
                }
            T savedRecord;
            try {
                savedRecord = repository.saveAndFlush(record);
            } catch (Exception e) {
                field++;
                fieldList.add(record);
                continue;
            }
            if (Objects.isNull(savedRecord)) {
                field++;
                fieldList.add(record);
            } else success++;
        }
        return OptRetMapUtil.saveOptResult(records.size(), success, field, fieldList);
    }

    public static <T> Map deleteByIds(BaseRepository<T> repository, List<String> ids) throws Exception {
        if (ToolUtil.isEmpty(ids)) return OptRetMapUtil.optError("删除的数据不能为空");
        Integer total = ids.size();
        Integer success = 0;
        Integer field = 0;
        List<String> fieldList = new ArrayList<>();
        for (String id : ids) {
            int deleted;
            try {
                deleted = repository.deleteRecordById(id);
            } catch (Exception e) {
                field++;
                fieldList.add(id);
                continue;
            }
            if (deleted <= 0) {
                field++;
                fieldList.add(id);
            } else success++;
        }
        return OptRetMapUtil.deleteOptResult(total, success, field, fieldList);
    }

    public static <T> Map select(BaseRepository<T> repository, QueryVO<T> queryVO, QueryStrategy queryStrategy, List<Sort.Order> orders) throws Exception {
        if (queryVO.isPageable()) {
            if (queryVO.getPage() < 0 || queryVO.getPageSize() <= 0)
                throw new ProjectException(ResultCode.exceptionError);
            Page page = repository.findAll(QueryStrategy.ofAllLikeMatch(queryVO, queryStrategy), queryVO.ofPage(orders));
            return OptRetMapUtil.selectOptResult(page);
        } else {
            List list = repository.findAll(QueryStrategy.ofAllLikeMatch(queryVO, queryStrategy), Sort.by(orders));
            return OptRetMapUtil.selectOptResult(list);
        }
    }
}
