package com.fish.entity;

import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */
@Data
@Entity
@Table(name = "fish_dict")
public class FishDict {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "code")
    private String code;

    @Column(name = "text")
    private String text;

    @Column(name = "sort")
    private Integer sort;
}
