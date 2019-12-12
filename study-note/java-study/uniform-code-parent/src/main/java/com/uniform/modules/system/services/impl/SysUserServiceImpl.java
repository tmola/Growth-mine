package com.uniform.modules.system.services.impl;


import com.uniform.common.annotation.DictResult;
import com.uniform.common.base.BaseServiceOperator;
import com.uniform.common.utils.IDUtil;
import com.uniform.common.utils.ObjectUtil;
import com.uniform.common.utils.OptRetMapUtil;
import com.uniform.common.utils.QueryStrategy;
import com.uniform.common.vo.QueryVO;
import com.uniform.modules.system.entity.SysUser;
import com.uniform.modules.system.repository.SysUserRepository;
import com.uniform.modules.system.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import java.util.*;

/**
 * 用户表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserRepository userRepository;


//    @Transactional(propagation = Propagation.NESTED)
    public Map save(List<SysUser> users) throws Exception {
//        Map map = null;
//        try {
//            map =  new BaseServiceOperator().save(userRepository, users);
//        }catch (Exception e){
//            e.getMessage();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }
//        return map;

        return new BaseServiceOperator().save(userRepository, users);
    }

    @Override
    public Map deleteByIds(List<String> ids) throws Exception {
        return new BaseServiceOperator().deleteByIds(userRepository, ids);
    }


    @DictResult
    @Override
    public Map select(QueryVO<SysUser> queryVO) throws Exception {
        QueryStrategy queryStrategy = new QueryStrategy();
        queryStrategy.setIgnoredFields("sexDictText");
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "createTime"));
        return BaseServiceOperator.select(userRepository, queryVO, queryStrategy, orders);
    }

    @Override
    public Map toSave(List<SysUser> users) {
        if (ObjectUtil.isEmpty(users)) return OptRetMapUtil.optError("保存的数据不能为空");
        Integer success = 0;
        Integer field = 0;
        Map<SysUser, String> fieldReasonMap = new LinkedHashMap<>();
        for (SysUser user : users) {
            if (ObjectUtil.isEmpty(user.getId())) {
                user.setId(IDUtil.randomID35());
                user.setDelFlag(0);
                user.setCreateTime(new Date());
            } else {
                user.setModifyTime(new Date());
            }
            SysUser savedRecord;
            try {
                savedRecord = this.userRepository.save(user);
            }catch (Exception e){
                field ++;
                fieldReasonMap.put(user, e.getMessage());
                continue;
            }
            if(Objects.nonNull(savedRecord))
                success++;
            else fieldReasonMap.put(user, "unknown reason");
        }
        return OptRetMapUtil.saveOptResult(users.size(), success,field, fieldReasonMap);
    }
}