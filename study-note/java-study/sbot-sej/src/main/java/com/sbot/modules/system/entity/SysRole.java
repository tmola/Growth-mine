package com.sbot.modules.system.entity;

import com.sbot.common.base.BaseField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Data
@Entity
@Table(name = "sys_role")
public class SysRole  extends BaseField {

    /**
     * 角色代码
     **/
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /**
     * 角色名称
     **/
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 描述
     **/
    @Column(name = "ROLE_DESC")
    private String roleDesc;


}
