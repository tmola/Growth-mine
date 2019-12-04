package com.uniform.modules.services.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.uniform.common.annotation.TranDict;
import com.uniform.common.base.BaseField;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseField {
    /**
     * 账号名称
     **/
    @Column(name = "NAME")
    @ExcelProperty(value = "账号名称", index = 0)
    private String name;

    /**
     * 真实姓名
     **/
    @Column(name = "REAL_NAME")
    @ExcelProperty(value = "真实姓名", index = 1)
    private String realName;

    /**
     * 账号密匙
     **/
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 盐
     **/
    @Column(name = "SLAT")
    private String slat;

    /**
     * 性别
     **/
    @TranDict(dict = "sex")
    @Column(name = "SEX")
    private String sex;

    @Transient
    private String sexDictText;

    /**
     * 出生日期
     **/
    @Temporal(TemporalType.DATE)
    @ExcelProperty(value = {"出生日期"}, index = 3)
    @Column(name = "BORNDATE")
    private Date borndate;

    /**
     * 联系电话
     **/
    @Column(name = "PHONE")
    private String phone;

    /**
     * 联系邮箱
     **/
    @Column(name = "EMAIL")
    private String email;

    /**
     * 联系地址
     **/
    @Column(name = "ADDRESS")
    private String address;



    /**
     * 实现Token的生成方法
     *
     * @param user
     */
    public static String createToken(SysUser user) {
        String token = "";
        //withAudience()存入需要保存在token的信息，这里把用户ID存入token中
        token = JWT.create().withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
