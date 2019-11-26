package com.sbot.modules.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 角色-权限关联表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Entity
@Table(name = "relev_role_auth")
public class RelevRoleAuth implements Serializable {

    /**
     * 主键ID
     **/
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 角色ID
     **/
    @Column(name = "ROLE_ID")
    private String roleId;

    /**
     * 权限ID
     **/
    @Column(name = "AUTHORITY_ID")
    private String authorityId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }
}
