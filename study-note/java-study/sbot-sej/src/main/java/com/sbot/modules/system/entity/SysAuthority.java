package com.sbot.modules.system.entity;


import com.sbot.common.base.BaseField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Data
@Entity
@Table(name = "sys_authority")
public class SysAuthority extends BaseField {

    /**
     * 权限代码
     **/
    @Column(name = "AUTHORITY_CODE")
    private String authorityCode;

    /**
     * 权限描述
     **/
    @Column(name = "AUTHORITY_DESC")
    private String authorityDesc;

}
