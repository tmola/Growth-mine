package com.design.module.system.entity;


import com.design.common.vo.CommonField;

import javax.persistence.*;

/**
 * 权限表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Entity
@Table(name="sys_authority")
public class SysAuthority extends CommonField {

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

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
