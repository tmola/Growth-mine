package com.code.modules.system.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;
import java.util.Date;

/**
 * 角色表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable {

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
     * 创建日期
     **/
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 修改日期
     **/
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    /**
     * 创建者
     **/
    @Column(name = "CREATE_USER")
    private String createUser;

    /**
     * 修改者
     **/
    @Column(name = "MODIFY_USER")
    private String modifyUser;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
