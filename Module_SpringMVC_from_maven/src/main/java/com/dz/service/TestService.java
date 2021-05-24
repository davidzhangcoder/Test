package com.dz.service;

import com.dz.control.ResourceSearchCriteria;
import com.dz.domain.Blog;
import com.dz.domain.Resource;
import com.dz.util.Tuple;

import java.util.List;

public interface TestService {

    public List<Blog> findAllUser();

    public void transfer();

    public Tuple<Long,List<Resource>> getBlogResource( ResourceSearchCriteria resourceSearchCriteria );

}
