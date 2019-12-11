package com.uniform.modules.entity;

import com.uniform.common.base.BaseField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * excel配置表
 */
@Data
@Entity
@Table(name = "sys_excel")
public class SysExcel  extends BaseField {

    /**
     * 主键ID
     **/
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 实体类名称
     **/
    @Column(name = "ENTITY_NAME")
    private String entityName;

    /**
     * 实体类地址（包名+'.'+类名）
     **/
    @Column(name = "ENTITY_CLAZZ")
    private String entityClazz;

    /**
     * excel类名称
     **/
    @Column(name = "EXCEL_NAME")
    private String excelName;

    /**
     * excel类地址（包名+'.'+类名）
     **/
    @Column(name = "EXCEL_CLAZZ")
    private String excelClazz;

    /**
     * 服务处理类名称
     **/
    @Column(name = "DEAL_SERVICE_NAME")
    private String dealServiceName;

    @Column(name = "begin_sheet")
    private Integer beginSheet;

    @Column(name = "begin_row")
    private Integer beginRow;

    @Column(name = "max_row")
    private Integer maxRow;

    /**
     * 服务处理类地址（包名+'.'+类名）
     **/
    @Column(name = "SERVICE_CLAZZ")
    private String serviceClazz;

    /**
     * 备注
     **/
    @Column(name = "REMARKS")
    private String remarks;
}
