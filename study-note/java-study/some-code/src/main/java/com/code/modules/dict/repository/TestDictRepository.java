package com.code.modules.dict.repository;

import com.code.modules.dict.entity.model.TestDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
@Repository
public interface TestDictRepository extends JpaRepository<TestDict, String>, JpaSpecificationExecutor {
}
