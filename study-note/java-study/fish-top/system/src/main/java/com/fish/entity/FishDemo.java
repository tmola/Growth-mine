package com.fish.entity;

import annotation.DictField;
import annotation.TextField;
import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */
@Data
@Entity
@Table(name = "fish_demo")
public class FishDemo {
    @Id
    @Column(name = "id")
    private String id;

    @DictField(dict = "sex")
    @Column(name = "sex")
    private String sex;

    @TextField(name = "sex")
    @Transient
    private String sexDictText;

    @Column(name = "income_type")
    private String incomeType;
    @Transient
    private String incomeTypeDictText;
}
