package com.dz.dao;

import com.dz.domain.Resource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository( value = "resourceDao" )
public class ResourceDao extends AbstractDao{

    @Autowired
    private SessionFactory sessionFactory;

    public Resource getResourceById(long resourceId) {
        return (Resource)this.getSession().get( Resource.class , (int)resourceId );
    }
}
