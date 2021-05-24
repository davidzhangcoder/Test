package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/springmvc")
public class SpringMVCController {

    @RequestMapping(value = "/doActioin" )
    public String doActioin(String username , String password) {
        System.out.println( username + " , " + password );
        return "success";
    }

    @RequestMapping(value = "/doActioinBean" , method = { RequestMethod.GET })
    @ResponseBody
    public String doActioinBean(User user) {
        System.out.println( user.toString() );
        return user.toString();
    }

    @RequestMapping(value = "/doActioin1" , method = { RequestMethod.POST })
    @ResponseBody
    public String doActioin1(String username , String password) {
        return username + " , " + password;
    }

    @RequestMapping(value = "/doActioin2" , method = { RequestMethod.POST })
    @ResponseBody
    public String doActioin2(User user) {
        System.out.println( user.toString() );
        return user.toString();
    }


}
