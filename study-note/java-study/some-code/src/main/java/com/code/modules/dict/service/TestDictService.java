package com.code.modules.dict.service;

import com.code.modules.dict.entity.model.TestDict;
import com.code.modules.dict.entity.vo.SearchVo;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
public interface TestDictService {

    List<TestDict> getAll() throws Exception;

    List<TestDict> searchAll(String search) throws Exception;

    Map searchAllByPage(SearchVo searchVo) throws Exception;

    void importData(MultipartFile file) throws Exception;
}
