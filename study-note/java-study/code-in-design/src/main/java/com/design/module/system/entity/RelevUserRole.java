package com.design.module.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户-角色关联表
 *
 */
@Entity
@Table(name="relev_user_role")
public class RelevUserRole implements Serializable {

/** 主键ID **/
    @Id
    @Column(name="ID" )
    private String id;

/** 用户ID **/
    @Column(name="USER_ID" )
    private String userId;

/** 角色ID **/
    @Column(name="ROLE_ID" )
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
