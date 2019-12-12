package components.base;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass //新增修改数据时能够更改
public class BaseField {
    /*主键id*/
    @Id
    @Column(name = "ID")
    private String id;

    /*删除标识：0正常，1已删除*/
    @Column(name = "DEL_FLAG")
    private Integer delFlag;

    /*创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    /*修改时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time")
    private Date modifyTime;

    /*创建者*/
    @Column(name = "create_user")
    private String createUser;

    /*修改者*/
    @Column(name = "modify_user")
    private String modifyUser;
}
