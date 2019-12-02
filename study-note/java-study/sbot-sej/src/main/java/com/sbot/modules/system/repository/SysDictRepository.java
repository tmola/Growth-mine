package com.sbot.modules.system.repository;


import com.sbot.common.base.BaseRepository;
import com.sbot.modules.system.entity.SysDict;

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
public interface SysDictRepository extends BaseRepository<SysDict> {
    @Transactional
    @Modifying
    @Query("update SysDict t set t.delFlag = 1  where t.id = ?1")
    Integer deleteRecordById(String id);

    @Query("select t.text from SysDict t  where t.delFlag = 0 and t.catalog = ?1 and t.code = ?2")
    String getText(String catalog, String code);

    @Query("select t.code from SysDict t  where t.delFlag = 0 and t.catalog = ?1 and t.text = ?2")
    String getCode(String catalog, String text);

    @Query("select t from SysDict t  where t.delFlag = 0 and t.catalog = ?1")
    List<SysDict> getDictList(String catalog);

    @Query("select t.text from SysDict t  where t.delFlag = 0 and t.catalog = ?1")
    List<String> getTextList(String catalog);


}
