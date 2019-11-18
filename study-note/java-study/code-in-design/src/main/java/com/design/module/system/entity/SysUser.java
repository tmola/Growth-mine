package com.design.module.system.entity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.design.common.vo.CommonField;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Entity
@Table(name="sys_user")
public class SysUser extends CommonField {

/** 主键id **/
    @Id
    @Column(name="ID" )
    private String id;

/** 账号名称 **/
    @Column(name="NAME" )
    private String name;

/** 真实姓名 **/
    @Column(name="REAL_NAME" )
    private String realName;

/** 账号密匙 **/
    @Column(name="PASSWORD" )
    private String password;

/** 盐 **/
    @Column(name="SLAT" )
    private String slat;

/** 性别 **/
    @Column(name="SEX" )
    private String sex;

/** 出生日期 **/
    @Temporal(TemporalType.DATE)
    @Column(name="BORNDATE" )
    private Date borndate;

/** 联系电话 **/
    @Column(name="PHONE" )
    private String phone;

/** 联系邮箱 **/
    @Column(name="EMAIL" )
    private String email;

/** 联系地址 **/
    @Column(name="ADDRESS" )
    private String address;

/** 删除标识：0正常，1已删除 **/
    @Column(name="DEL_FLAG" )
    private int delFlag;

    /**
     * 实现Token的生成方法
     * @param user
     */
    public static String createToken(SysUser user) {
        String token="";
        //withAudience()存入需要保存在token的信息，这里把用户ID存入token中
        token= JWT.create().withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getSlat() {
        return slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBorndate() {
        return borndate;
    }

    public void setBorndate(Date borndate) {
        this.borndate = borndate;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
