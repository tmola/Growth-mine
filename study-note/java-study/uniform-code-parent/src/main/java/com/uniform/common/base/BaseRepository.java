package com.uniform.common.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/2 21:56
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, String>, JpaSpecificationExecutor {

    Integer deleteRecordById(String id);
}
