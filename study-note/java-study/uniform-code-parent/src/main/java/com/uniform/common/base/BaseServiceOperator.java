package com.uniform.common.base;

import com.uniform.common.enums.ResultCode;
import com.uniform.common.enums.SqlExceptionCode;
import com.uniform.common.exception.BusinessException;
import com.uniform.common.utils.*;
import com.uniform.common.vo.QueryVO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.sql.SQLException;
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
//    @Autowired
//    private  PlatformTransactionManager transactionManager;

    @Transactional
    @Modifying
    public <T> Map save(BaseRepository<T> repository, List<T> records) throws Exception {
        if (ObjectUtil.isEmpty(records)) return OptRetMapUtil.optError("保存的数据不能为空");
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
        Map<T, String> fieldReasonMap = new LinkedHashMap<>();
        for (T record : records) {
            if (Objects.nonNull(setId))
                if (ObjectUtil.isEmpty(getId.invoke(record))) {
                    if (Objects.nonNull(setId))
                        setId.invoke(record, IDUtil.randomID35());
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
                String reason;
                if(e instanceof DuplicateKeyException)reason ="主键约束";
                else if(e instanceof DataIntegrityViolationException)reason ="唯一性约束";
                else reason = e.toString();
                fieldReasonMap.put(record, reason);
                continue;
            }
            if (Objects.nonNull(savedRecord)) {
                success++;

            } else {
                field++;
                fieldReasonMap.put(record, "unknown reason");
            }
        }
        return OptRetMapUtil.saveOptResult(records.size(), success, field, fieldReasonMap);
    }

    @Transactional
    @Modifying
    public <T> Map deleteByIds(BaseRepository<T> repository, List<String> ids) throws Exception {
        if (ObjectUtil.isEmpty(ids)) return OptRetMapUtil.optError("删除的数据不能为空");
        Integer total = ids.size();
        Integer success = 0;
        Integer field = 0;
        Map fieldReasonMap = new LinkedHashMap();
        for (String id : ids) {
            int deleted;
            try {
                deleted = repository.deleteRecordById(id);
            } catch (Exception e) {
                field++;
                fieldReasonMap.put(id, e.toString());
                continue;
            }
            if (deleted <= 0) {
                field++;
                fieldReasonMap.put(id, "unknown reason");
            } else success++;
        }
        return OptRetMapUtil.deleteOptResult(total, success, field, fieldReasonMap);
    }

    public static <T> Map select(BaseRepository<T> repository, QueryVO<T> queryVO, QueryStrategy queryStrategy, List<Sort.Order> orders) throws Exception {
        if (queryVO.getPageQuery()) {
            if (queryVO.getPage() < 0 || queryVO.getPageSize() <= 0)
                throw new BusinessException(ResultCode.serviceException);
            Page page = repository.findAll(QueryStrategy.ofAllLikeMatch(queryVO, queryStrategy), queryVO.ofPage(orders));
            return OptRetMapUtil.selectOptResult(page);
        } else {
            List list = repository.findAll(QueryStrategy.ofAllLikeMatch(queryVO, queryStrategy), Sort.by(orders));
            return OptRetMapUtil.selectOptResult(list);
        }
    }
}
