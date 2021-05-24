package com.dz.control;

import com.dz.domain.Resource;
import com.dz.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;


    @RequestMapping( value = "/getResource" , method = { RequestMethod.POST , RequestMethod.GET } )
    public ModelAndView getResource( @RequestParam( name = "resourceId" , required = true ) long resourceId ){

        Resource resource = resourceService.getResourceById(resourceId);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("editresourcemodal");
        mv.addObject(resource);
        return mv;

//        return "editresourcemodal";
//        return UrlBasedViewResolver.FORWARD_URL_PREFIX + "/WEB-INF/pages/modal.html";
    }

    @RequestMapping( value = "/updateResource" )
    @ResponseBody
    public Map<String, Object> updateResource(Resource resource ) {
        Resource updatedResource = resourceService.updateResource( resource );
        System.out.println( resource );

        Map<String, Object> map=new HashMap<String, Object>();
        map.put("status", 200);
        return map;
    }

    @RequestMapping( value = "/saveResource" )
    @ResponseBody
    public Map<String, Object> saveResource(Resource resource ) {
        Resource updatedResource = resourceService.saveResource( resource );
        System.out.println( resource );

        Map<String, Object> map=new HashMap<String, Object>();
        map.put("status", 200);
        return map;
    }


}
