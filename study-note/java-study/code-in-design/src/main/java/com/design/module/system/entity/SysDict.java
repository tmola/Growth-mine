package com.design.module.system.entity;

import com.design.common.vo.CommonField;

import javax.persistence.*;

/**
 * 字典表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Entity
@Table(name="sys_dict")
public class SysDict extends CommonField {

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

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
