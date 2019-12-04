package com.uniform.modules.services.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Data
@Entity
@Table(name = "sys_log")
public class SysLog implements Serializable {
    /**
     * 主键ID
     **/
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 日志等级
     **/
    @Column(name = "LOG_LEVEL")
    private String logLevel;

    /**
     * 日志类型
     **/
    @Column(name = "LOG_TYPE")
    private String logType;

    /**
     * 日志内容
     **/
    @Column(name = "LOG_CONTENT")
    private String logContent;

    /**
     * 请求方法名
     **/
    @Column(name = "REQ_METHOD")
    private String reqMethod;

    /**
     * 请求路径
     **/
    @Column(name = "REQ_URL")
    private String reqUrl;

    /**
     * 请求参数
     **/
    @Column(name = "REQ_PARAMS")
    private String reqParams;

    /**
     * 请求返回结果
     **/
    @Column(name = "REQ_RESULT")
    private String reqResult;

    /**
     * 请求IP
     **/
    @Column(name = "REQ_IP")
    private String reqIp;

    /**
     * 操作类型
     **/
    @Column(name = "OPT_TYPE")
    private String optType;

    /**
     * 操作用户
     **/
    @Column(name = "OPT_USER")
    private String optUser;

    /**
     * 操作时间
     **/
    @Temporal(TemporalType.DATE)
    @Column(name = "OPT_DATE")
    private Date optDate;

    /**
     * 操作耗时
     **/
    @Column(name = "OPT_COST_TIME")
    private Long optCostTime;

}
