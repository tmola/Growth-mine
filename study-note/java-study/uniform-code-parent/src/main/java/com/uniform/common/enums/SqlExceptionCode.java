package com.uniform.common.enums;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/5
 */
public enum SqlExceptionCode {

    dataIntegrityViolation(DataIntegrityViolationException.class, "违反唯一性约束"),
    duplicateKey(DuplicateKeyException.class,"违反主键约束")
    ;
    public Class clas;
    public String info;
    SqlExceptionCode(Class clas, String info){
        this.clas = clas;
        this.info = info;
    }
}
