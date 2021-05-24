package com.dz.control;

import com.dz.domain.Blog;
import com.dz.domain.Resource;
import com.dz.service.TestService;
import com.dz.util.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/findAllUser")
    @ResponseBody
    public String findAllUser() {
        System.out.println("TestController is running");
        List<Blog> blogList = testService.findAllUser();

        for( Blog blog : blogList ) {
            System.out.println( blog.toString() );
        }
        return "success";
    }

    @RequestMapping("/transfer")
    @ResponseBody
    public String transfer() {
        System.out.println("TestController is running");
        testService.transfer();

        return "success";
    }

    //1.（1）当通过ajax传入json数据(即contentType: 'application/json')，可以用@RequestBody绑定到java对象
    //(2)当使用contentType : "application/x-www-form-urlencoded"传入时（json会自动拼接成字符串传入，即a=..&b=..）,也会绑定到java对象，好像字符串中每个key都要有值
    @RequestMapping("/getBlogResource")
    @ResponseBody
    public Map<String,Object> getBlogResource( @RequestBody ResourceSearchCriteria resourceSearchCriteria
//            @RequestParam( name="pageSize" , required = true ) int pageSize,
//            @RequestParam( name="page" , required = true ) int page
    ) {
        resourceSearchCriteria.setPage( resourceSearchCriteria.getPage() - 1 );
        Tuple<Long,List<Resource>> tuple = testService.getBlogResource( resourceSearchCriteria );
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total",tuple.first());
        map.put("list",tuple.second());
        return map;
    }

    @RequestMapping("/getModal")
    public String getModal(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("modal.html");

        System.out.println("forward:/WEB-INF/pages/success.jsp");
//        return "forward:/WEB-INF/pages/success.jsp";
        return UrlBasedViewResolver.FORWARD_URL_PREFIX + "/WEB-INF/pages/modal.html";
    }


    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}
