package com.uniform.modules.entity;

import com.uniform.common.base.BaseField;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 字典表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Data
@Entity
@Table(name = "sys_dict")
public class SysDict extends BaseField {

    /**
     * 主键
     **/
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 目录
     **/
    @Column(name = "CATALOG")
    private String catalog;

    /**
     * 代码
     **/
    @Column(name = "CODE")
    private String code;

    /**
     * 描述
     **/
    @Column(name = "TEXT")
    private String text;

    /**
     * 排序
     **/
    @Column(name = "SORT")
    private Integer sort;
}
