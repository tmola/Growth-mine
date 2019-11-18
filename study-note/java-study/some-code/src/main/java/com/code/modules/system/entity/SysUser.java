package com.code.modules.system.entity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
public class SysUser implements Serializable {

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

    public void setAddress(String address) {
        this.address = address;
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
