package com.design.module.system.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/19
 */
@Data
@Entity
@Table(name = "sys_file")
public class SysFile implements Serializable {
    @Id
    @Column(name="id" )
    private String id;

    @Column(name="file_name" )
    private String fileName;

    @Column(name="file_size" )
    private Integer fileSize;

    @Column(name="file_type" )
    private String fileType;

    @Column(name="path_url" )
    private String pathUrl;

    @Column(name="user_id" )
    private String userId;

    @Column(name="file_abs" )
    private String fileAbs;

    @Temporal(TemporalType.DATE)
    @Column(name="upload_date" )
    private Date uploadDate;

}
