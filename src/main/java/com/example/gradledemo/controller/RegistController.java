package com.example.gradledemo.controller;


import com.example.gradledemo.domain.Userlogin;
import com.example.gradledemo.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController //数据按json格式返回
@RequestMapping("/user")
public class RegistController {

    @Autowired
    private UserloginService userloginService;

    /*
       @RequestMapping(value = "/doRegist")
        public String doRegist(Userlogin userlogin, Model model){
           System.out.println(userlogin.getUsername());
           userloginService.regist(userlogin);
           return "registsuccess";
       }
    */
    @RequestMapping(value = "/doRegist", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @CrossOrigin
    public String doRegist(@RequestBody Userlogin userlogin) {
        userloginService.saveOrUpdate(userlogin);
        return "registsuccess";
    }


    @RequestMapping(value = "/wxuserdoRegist", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    //@PostMapping("/wxuserdoRegist")
    @CrossOrigin
    public Map<String, Object> wxuserdoRegist(Userlogin userlogin) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        userloginService.saveOrUpdate(userlogin);
        map.put("userdoRegist", "true");
        return map;
    }
}
