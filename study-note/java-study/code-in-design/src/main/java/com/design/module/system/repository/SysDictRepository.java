package com.design.module.system.repository;


import com.design.module.system.entity.SysDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Repository
public interface SysDictRepository extends JpaRepository<SysDict, String>,JpaSpecificationExecutor {
    @Transactional
    @Modifying
    @Query("update SysDict t set t.delFlag = 1  where t.id = ?1")
    SysDict delelteById(String id);

    @Query("select t.text from SysDict t  where t.delFlag = 0 and t.catalog = ?1 and t.code = ?2")
    String getTextByCode(String catalog, String code);

    @Query("select t.catalog, t.code, t.text from SysDict t  where t.delFlag = 0 and t.catalog = ?1")
    List<SysDict> getTextList(String catalog);
}
