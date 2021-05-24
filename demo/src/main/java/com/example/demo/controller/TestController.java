package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author David
 * @date 2020-05-08 - 11:19 p.m.
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "AAAAA Test1-6";
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "dalaoyang");
        return "index";
    }

    @RequestMapping("/testParameter")
    @ResponseBody
    public String testParameter(@RequestParam String username) {
        System.out.println( username );
        return username;
    }


}
