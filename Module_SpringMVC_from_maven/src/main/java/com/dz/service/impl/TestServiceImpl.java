package com.dz.service.impl;

import com.dz.control.ResourceSearchCriteria;
import com.dz.dao.TestDao;
import com.dz.domain.Account;
import com.dz.domain.Blog;
import com.dz.domain.Resource;
import com.dz.service.TestService;
import com.dz.util.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service( value = "testServiceImpl" )
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }

    @Override
    public List<Blog> findAllUser() {
        System.out.println("Service Layer running");
        return testDao.findAllBlog();
    }

    @Override
    public void transfer() {
        Account account1 = testDao.findAccountByName("aaa");
        Account account2 = testDao.findAccountByName("bbb");

        account1.setBalance( account1.getBalance() - 100);
        account2.setBalance( account2.getBalance() + 100);

        testDao.save( account1 );

//        int a = 1/0;

        testDao.save( account2 );

        System.out.println(account1);
        System.out.println(account2);
    }

    @Override
    public Tuple<Long,List<Resource>> getBlogResource( ResourceSearchCriteria resourceSearchCriteria ) {
        Long count = testDao.getBlogcount( resourceSearchCriteria );
        List<Resource> list = testDao.getBlogResource( resourceSearchCriteria );
        Tuple<Long,List<Resource>> tuple = new Tuple<Long,List<Resource>>(count,list);
        return tuple;
    }

}
