package com.code.modules.dict.service.impl;

import com.code.common.util.ObjectUtil;
import com.code.common.util.result.ServiceResult;
import com.code.modules.dict.entity.model.TestDict;
import com.code.modules.dict.entity.vo.SearchVo;
import com.code.modules.dict.repository.TestDictRepository;
import com.code.modules.dict.service.TestDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TestDict> getAll() {
        return repository.findAll();
    }

    @Override
    public List<TestDict> searchAll(String search) {
        CriteriaBuilder cb = em.getCriteriaBuilder(); // criteriaQuery工厂
        CriteriaQuery cq = cb.createQuery();//查询语句构造器
        Root<TestDict> root = cq.from(TestDict.class);//获取查询根对象
        //select t from TestDict t
        cq.select(root);
//        //select t.id from TestDict t
//        cq.select(root.get("id"));
//        //select t.id, t.name, t.sex from TestDict t
//        cq.multiselect(root.get("id"), root.get("name"), root.get("sex"));
        if (search != null && !search.equals("null") && !search.equals("")) {
            //构造查询条件
            Predicate nameLike = cb.like(root.get("name").as(String.class), search); // name like %search%
            Predicate sexEquel = cb.equal(root.get("sex").as(String.class), search); // sex = search
            cq.orderBy(cb.desc(root.get("ino")));// order by ino desc
            cq.where(cb.or(nameLike, sexEquel));
            // select t from TestDict t where name like %search% or sex = search order by ino desc
        }
        Query query = em.createQuery(cq);
//        query.setParameter("name", search);
        List<TestDict> list = query.getResultList();
        return list;
    }

    @Override
    public Map searchAllByPage(SearchVo searchVo) throws Exception {

        boolean flag = false;
        String dataSql = "select t from TestDict t where 1 = 1";
        String countSql = "select count(t) from TestDict t where 1 = 1";

        if (ObjectUtil.isNotEmpty(searchVo.getSearch())) {
            String where = " and t.name like %|| :name ||% or t.sex = :sex";
            dataSql += where;
            countSql += where;
            flag = true;
        }
        Query dataQuery = em.createQuery(dataSql);
        dataQuery.setFirstResult(searchVo.getPage() * searchVo.getPageSize());
        dataQuery.setMaxResults(searchVo.getPage() * searchVo.getPageSize() + searchVo.getPageSize());
        Query countQuery = em.createQuery(countSql);
        countQuery.setFirstResult(searchVo.getPage() * searchVo.getPageSize());
        countQuery.setMaxResults(searchVo.getPage() * searchVo.getPageSize() + searchVo.getPageSize());

        if (flag) {
            dataQuery.setParameter("name", searchVo.getSearch());
            dataQuery.setParameter("sex", searchVo.getSearch());
            countQuery.setParameter("name", searchVo.getSearch());
            countQuery.setParameter("sex", searchVo.getSearch());
        }
        long count = (long) countQuery.getSingleResult();
        List<TestDict> data = dataQuery.getResultList();

        return ServiceResult.result(data, searchVo.page());
    }

    @Override
    public void importData(MultipartFile file) {


    }


}
