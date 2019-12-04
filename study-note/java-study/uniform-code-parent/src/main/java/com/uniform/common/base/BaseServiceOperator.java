package com.uniform.common.base;

import com.uniform.common.enums.ResultCode;
import com.uniform.common.exception.BusinessException;
import com.uniform.common.utils.*;
import com.uniform.common.vo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.reactive.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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
//    @Autowired
//    private  PlatformTransactionManager transactionManager;

    @Transactional(noRollbackFor = RuntimeException.class)
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
        List<T> fieldList = new ArrayList<>();
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
            TransactionStatus getTransaction = null;
//            try {
                savedRecord = repository.saveAndFlush(record);
                getTransaction = TransactionAspectSupport.currentTransactionStatus();
//            } catch (Exception e) {
//                e.getMessage();
//                field++;
//                fieldList.add(record);
//                if(e instanceof RuntimeException) {
//                    throw  e;
////                    transactionManager.rollback(getTransaction);
//                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                }
//                continue;
//            }
            if (Objects.isNull(savedRecord)) {
                field++;
                fieldList.add(record);
            } else {
//                transactionManager.commit(getTransaction);
                success++;
            }
        }
        return OptRetMapUtil.saveOptResult(records.size(), success, field, fieldList);
    }

    public static <T> Map deleteByIds(BaseRepository<T> repository, List<String> ids) throws Exception {
        if (ObjectUtil.isEmpty(ids)) return OptRetMapUtil.optError("删除的数据不能为空");
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
