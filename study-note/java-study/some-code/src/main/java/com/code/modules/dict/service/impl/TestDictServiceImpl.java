package com.code.modules.dict.service.impl;

import com.code.modules.dict.entity.model.TestDict;
import com.code.modules.dict.repository.TestDictRepository;
import com.code.modules.dict.service.TestDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
@Service
public class TestDictServiceImpl implements TestDictService {
    @Autowired
    private TestDictRepository repository;
    @Override
    public List<TestDict> getAll() {
        return repository.findAll();
    }
}
