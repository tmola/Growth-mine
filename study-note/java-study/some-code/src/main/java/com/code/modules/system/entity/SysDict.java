package com.code.modules.system.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;
import java.util.Date;

/**
 * 字典表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Entity
@Table(name="sys_dict")
public class SysDict implements Serializable {

/** 主键 **/
    @Id
    @Column(name="ID" )
    private String id;

/** 目录 **/
    @Column(name="CATALOG" )
    private String catalog;

/** 代码 **/
    @Column(name="CODE" )
    private String code;

/** 描述 **/
    @Column(name="TEXT" )
    private String text;

/** 排序 **/
    @Column(name="SORT" )
    private int sort;

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
    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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
