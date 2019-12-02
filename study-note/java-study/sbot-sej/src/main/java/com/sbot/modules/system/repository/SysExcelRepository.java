package com.sbot.modules.system.repository;

import com.sbot.modules.system.entity.SysDict;
import com.sbot.modules.system.entity.SysExcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/2
 */
public interface SysExcelRepository extends JpaRepository<SysExcel, String>, JpaSpecificationExecutor {

    @Query(value = "select t from SysExcel t where t.delFlag=0 \n " +
            "and t.entityName = ?1\n" +
            "and t.excelName = ?2\n")
    SysExcel getByName(String entityName, String excelName);
}
