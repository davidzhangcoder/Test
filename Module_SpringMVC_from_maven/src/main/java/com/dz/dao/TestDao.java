package com.dz.dao;

import com.dz.control.ResourceSearchCriteria;
import com.dz.domain.Account;
import com.dz.domain.Blog;
import com.dz.domain.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository( value = "testDao" )
public class TestDao extends AbstractDao{

    @Autowired
    private SessionFactory sessionFactory;

//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

//    private Session getSession(){
////        try {
//            return sessionFactory.getCurrentSession();
////        }catch(Exception e) {
////            return sessionFactory.openSession();
////        }
//    }

    public List<Blog> findAllBlog() {
        System.out.println("DAO is running");
        return this.getSession().createQuery("from Blog ").list();
    }

    public Account findAccountByName( String name ) {
        System.out.println( "transaction: " + getSession().getTransaction() );
        Query query = this.getSession().createQuery(" from Account where name = :name");
        query.setString( "name" , name );
        Object object = query.uniqueResult();
        if( object == null )
            return null;
        return (Account) object;
    }

    public List<Resource> getBlogResource( ResourceSearchCriteria resourceSearchCriteria ) {
        int pageSize = resourceSearchCriteria.getPageSize();
        int page = resourceSearchCriteria.getPage();

        Query query = getQuery(resourceSearchCriteria , " from Resource ");

        query.setMaxResults(pageSize );
        query.setFirstResult(pageSize * page );
        List list = query.list();
        return list;
    }

    private Query getQuery(ResourceSearchCriteria resourceSearchCriteria , String fromString) {
        StringBuffer sb = new StringBuffer( fromString );
        sb.append(" where 1 = 1 ");
        if( resourceSearchCriteria != null && resourceSearchCriteria.getIdSearch() != 0 ) {
            sb.append(" and id = :id ");
        }
        if( resourceSearchCriteria != null && resourceSearchCriteria.getTitleSearch()!=null && !resourceSearchCriteria.getTitleSearch().trim().equals("") ) {
            sb.append(" and title like :title ");
        }
        sb.append(" order by id desc ");

        Query query = this.getSession().createQuery(sb.toString() );

        if( resourceSearchCriteria != null && resourceSearchCriteria.getIdSearch() != 0 ) {
            query.setLong("id" , resourceSearchCriteria.getIdSearch() );
        }
        if( resourceSearchCriteria != null && resourceSearchCriteria.getTitleSearch()!=null && !resourceSearchCriteria.getTitleSearch().trim().equals("") ){
            query.setString("title" , "%"+resourceSearchCriteria.getTitleSearch()+"%" );
        }
        return query;
    }

    public Long getBlogcount( ResourceSearchCriteria resourceSearchCriteria ){

        Query query = getQuery(resourceSearchCriteria , " select count(*) from Resource " );

        //Query query = this.getSession().createQuery(" select count(*) from Resource");
        Object object = query.uniqueResult();
        return (Long) object;
    }

}
