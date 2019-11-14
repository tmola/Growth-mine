package com.code.modules.dict.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
@Data
@Table(name = "dict")
@Entity
public class Dict {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "catalog")
    private String catalog;

    @Column(name = "code")
    private String code;

    @Column(name = "text")
    private String text;

    @Column(name = "sort")
    private Integer sort;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "modify_user")
    private String modifyUser;

    @Column(name = "del_flag")
    private int delFlag;
}
