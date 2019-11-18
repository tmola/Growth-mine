package com.design.module.system.entity;

import com.design.common.vo.CommonField;

import javax.persistence.*;

/**
 * 角色表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Entity
@Table(name = "sys_role")
public class SysRole  extends CommonField {

    /**
     * 主键id
     **/
    @Id
    @Column(name = "ID")
    private String id;

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

    /**
     * 删除标识：0正常，1已删除
     **/
    @Column(name = "DEL_FLAG")
    private int delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }


    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
