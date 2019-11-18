package com.code.modules.dict.entity.model;

import com.code.common.dictAnnotation.TranDict;
import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
@Data
@Table(name = "test_dict")
@Entity
public class TestDict {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @TranDict(dict = "sex")
    @Column(name = "sex")
    private String sex;

    @Column(name = "ino")
    private String ino;

//    @ManyToMany()
//    private Dict dict;
}
