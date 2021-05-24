package com.dz.service;

import com.dz.dao.TestDao;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TestServiceTester {

    @Test
    public void testFindAllUser() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        TestService ts = (TestService) ac.getBean("testServiceImpl");

        ts.findAllUser();
    }

    @Test
    public void testGetAccountByName() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
        sessionFactory.openSession();
        TestDao testDao = (TestDao) ac.getBean("testDao");
        System.out.println( testDao.findAccountByName("aaa") );
    }

}
