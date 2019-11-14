package com.code.modules.dict.repository;

import com.code.modules.dict.entity.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
@Repository
public interface DictRepository extends JpaRepository<Dict, String>, JpaSpecificationExecutor {

//    @Transactional
//    @Modifying
//    @Query("update Dict t set t.DFlag = 1  where t.id in ?1")
//    int delelteByIds(List<String> ids);

    @Transactional
    @Modifying
    @Query("update Dict t set t.delFlag = 1  where t.id = ?1")
    Dict delelteById(String id);

    @Query("select t.text from Dict t  where t.delFlag = 0 and t.catalog = ?1 and t.code = ?2")
    String getTextByCode(String catalog, String code);

    @Query("select t.catalog, t.code, t.text from Dict t  where t.delFlag = 0 and t.catalog = ?1")
    List<Dict> getTextList(String catalog);

}
