package com.sbot.modules.system.entity;

import com.sbot.common.vo.BaseField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 字典表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Data
@Entity
@Table(name="sys_dict")
public class SysDict extends BaseField {

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
    private Integer sort;

/** 删除标识：0正常，1已删除 **/
    @Column(name="DEL_FLAG" )
    private Integer delFlag;
}
