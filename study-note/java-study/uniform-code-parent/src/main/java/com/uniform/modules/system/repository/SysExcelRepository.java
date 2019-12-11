package com.uniform.modules.repository;

import com.uniform.common.base.BaseRepository;
import com.uniform.modules.entity.SysExcel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/2
 */
public interface SysExcelRepository extends BaseRepository<SysExcel> {

    @Transactional
    @Modifying
    @Query(value = "update SysExcel t set t.delFlag=1 where t.delFlag=0 \n " +
            "and t.id = ?1\n")
    Integer deleteRecordById(String id);

    @Query(value = "select t from SysExcel t where t.delFlag=0 \n " +
            "and t.entityName = ?1\n" +
            "and t.excelName = ?2\n")
    SysExcel getByName(String entityName, String excelName);

    @Query(value = "select distinct t from SysExcel t where t.delFlag=0 \n " +
            "and t.entityName = ?1\n")
    SysExcel getByName(String entityName);
}
