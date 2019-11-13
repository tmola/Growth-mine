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
    @Column(name = "c_time")
    private Date cTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "m_time")
    private Date mTime;

    @Column(name = "c_user")
    private String cUser;

    @Column(name = "m_user")
    private String mUser;

    @Column(name = "d_flag")
    private int dFlag;
}
