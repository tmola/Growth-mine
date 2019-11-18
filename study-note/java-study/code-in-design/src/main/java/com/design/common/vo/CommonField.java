package com.design.common.vo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/17 3:52
 */
@Data
public class CommonField implements Serializable {

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
}
