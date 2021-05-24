package com.dz.service.impl;

import com.dz.dao.ResourceDao;
import com.dz.domain.Resource;
import com.dz.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service( value = "resourceServiceImpl" )
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public Resource getResourceById(long resourceId) {
        return resourceDao.getResourceById( resourceId );
    }

    @Override
    public Resource updateResource(Resource resource) {
        return (Resource)resourceDao.update( resource );
    }

    @Override
    public Resource saveResource(Resource resource) {
        return (Resource)resourceDao.save( resource );
    }
}
