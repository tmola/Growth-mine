package com.sbot.common.base;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/2 21:15
 */
@Data
@MappedSuperclass
public class BaseField {
    /**
     * 主键id
     **/
    @Id
    @Column(name = "ID")
    private String id;

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

    /**
     * 删除标识：0正常，1已删除
     **/
    @Column(name = "DEL_FLAG")
    private Integer delFlag;


}
