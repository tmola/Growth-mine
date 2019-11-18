package com.code.modules.system.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统日志表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Entity
@Table(name="sys_log")
public class SysLog implements Serializable {

/** 主键ID **/
    @Id
    @Column(name="ID" )
    private String id;

/** 日志等级 **/
    @Column(name="LOG_LEVEL" )
    private String logLevel;

/** 日志类型 **/
    @Column(name="LOG_TYPE" )
    private String logType;

/** 日志内容 **/
    @Column(name="LOG_CONTENT" )
    private String logContent;

/** 请求方法名 **/
    @Column(name="REQ_METHOD" )
    private String reqMethod;

/** 请求路径 **/
    @Column(name="REQ_URL" )
    private String reqUrl;

/** 请求参数 **/
    @Column(name="REQ_PARAMS" )
    private String reqParams;

/** 请求返回结果 **/
    @Column(name="REQ_RESULT" )
    private String reqResult;

/** 请求IP **/
    @Column(name="REQ_IP" )
    private String reqIp;

/** 操作类型 **/
    @Column(name="OPT_TYPE" )
    private String optType;

/** 操作用户 **/
    @Column(name="OPT_USER" )
    private String optUser;

/** 操作时间 **/
    @Temporal(TemporalType.DATE)
    @Column(name="OPT_DATE" )
    private Date optDate;

/** 操作耗时 **/
    @Column(name="OPT_COST_TIME" )
    private Long optCostTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }
    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }
    public String getReqParams() {
        return reqParams;
    }

    public void setReqParams(String reqParams) {
        this.reqParams = reqParams;
    }
    public String getReqResult() {
        return reqResult;
    }

    public void setReqResult(String reqResult) {
        this.reqResult = reqResult;
    }
    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }
    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }
    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser;
    }
    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }
    public Long getOptCostTime() {
        return optCostTime;
    }

    public void setOptCostTime(Long optCostTime) {
        this.optCostTime = optCostTime;
    }
}
