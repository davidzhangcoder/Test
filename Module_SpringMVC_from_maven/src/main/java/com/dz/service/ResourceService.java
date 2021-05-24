package com.dz.service;

import com.dz.domain.Resource;

public interface ResourceService {

    public Resource getResourceById(long resourceId );

    public Resource updateResource(Resource resource);

    public Resource saveResource(Resource resource);
}
