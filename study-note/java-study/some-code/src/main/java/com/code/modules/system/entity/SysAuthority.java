package com.code.modules.system.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 权限表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Entity
@Table(name="sys_authority")
public class SysAuthority implements Serializable {

/** 主键id **/
    @Id
    @Column(name="ID" )
    private String id;

/** 权限代码 **/
    @Column(name="AUTHORITY_CODE" )
    private String authorityCode;

/** 权限描述 **/
    @Column(name="AUTHORITY_DESC" )
    private String authorityDesc;

/** 创建日期 **/
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME" )
    private Date createTime;

/** 修改日期 **/
    @Temporal(TemporalType.DATE)
    @Column(name="MODIFY_TIME" )
    private Date modifyTime;

/** 创建者 **/
    @Column(name="CREATE_USER" )
    private String createUser;

/** 修改者 **/
    @Column(name="MODIFY_USER" )
    private String modifyUser;

/** 删除标识：0正常，1已删除 **/
    @Column(name="DEL_FLAG" )
    private int delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }
    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
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
